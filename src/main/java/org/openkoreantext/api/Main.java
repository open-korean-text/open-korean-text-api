package org.openkoreantext.api;

import static spark.Spark.*;

import org.openkoreantext.api.controller.KoreanTextController;
import org.openkoreantext.api.util.CORSFilter;
import org.openkoreantext.api.util.Path;
import org.openkoreantext.api.util.Port;

public class Main {

	public static void main(String[] args) {
		port(Port.getHerokuAssignedPort());
		
		CORSFilter.enable();
		
		get(Path.NORMALIZE,			KoreanTextController.normalize);
		get(Path.TOKENIZE,			KoreanTextController.tokenize);
		get(Path.EXTRACT_PHRASE,	KoreanTextController.extractPhrase);
		
		post(Path.NORMALIZE,		KoreanTextController.normalizePost);
		post(Path.TOKENIZE,			KoreanTextController.tokenizePost);
		post(Path.EXTRACT_PHRASE,	KoreanTextController.extractPhrasePost);
	}

}
