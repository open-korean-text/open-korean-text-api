package org.openkoreantext.api;

import java.util.List;

import org.json.simple.JSONObject;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;

import com.google.common.collect.ImmutableMap;

import scala.collection.Seq;

public class TokenizerResource {
	
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
		return new JSONObject(
		        ImmutableMap.of(
		            "tokens", OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens),
		            "token_strings", OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens)
		        ));
	}
	
	public String stemGet(String text) {
		return stemJson(text).toJSONString();
	}
	
	private JSONObject stemJson(String text) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		Seq<KoreanTokenizer.KoreanToken> stemmed = OpenKoreanTextProcessorJava.stem(tokens);
		return new JSONObject(
		        ImmutableMap.of(
		            "tokens", OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(stemmed),
		            "token_strings", OpenKoreanTextProcessorJava.tokensToJavaStringList(stemmed)
		        ));
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
		            "phrases", phrases
        		));
	}

}
