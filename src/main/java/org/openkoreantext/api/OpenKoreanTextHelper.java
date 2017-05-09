package org.openkoreantext.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;

import com.google.common.collect.ImmutableMap;

import scala.collection.Seq;

public class OpenKoreanTextHelper {
	
	public String normalizeGet(String text) {
		return normalizeJson(text).toJSONString();
	}
	
	private JSONObject normalizeJson(String text) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		return new JSONObject(
				ImmutableMap.of(
					"strings", normalized
				));
	}
	
	public String tokenizeGet(String text) {
		return tokenizeJson(text).toJSONString();
	}
	
	private JSONObject tokenizeJson(String text) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		List<KoreanTokenJava> tokenList = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
		
		return new JSONObject(
		        ImmutableMap.of(
		            "tokens", convertTokenStrList(tokenList),
		            "token_strings", OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens)
		        ));
	}
	
	private <T> List<String> convertTokenStrList(List<T> tokenList) {
		List<String> tokenStrList = new ArrayList<String>();
		for (Object token: tokenList) {
			tokenStrList.add(token.toString());
		}
		return tokenStrList;
	}
	
	public String extractPhrasesGet(String text) {
		return extractPhrasesJson(text).toJSONString();
	}
	
	private JSONObject extractPhrasesJson(String text) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);
		
		return new JSONObject(
		        ImmutableMap.of(
		            "phrases", convertTokenStrList(phrases)
        		));
	}

}
