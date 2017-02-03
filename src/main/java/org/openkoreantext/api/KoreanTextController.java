package org.openkoreantext.api;

import static spark.Spark.get;

public class KoreanTextController {
	
	TokenizerResource tokenizerResource = new TokenizerResource();
	
	public KoreanTextController() {
		get("/normalize", (req, res) -> {
			String text = req.queryParams("text");
			return tokenizerResource.normalizeGet(text);
		});
		
		get("/tokenize", (req, res) -> {
			String text = req.queryParams("text");
			return tokenizerResource.tokenizeGet(text);
		});
		
		get("/stem", (req, res) -> {
			String text = req.queryParams("text");
			return tokenizerResource.stemGet(text);
		});
		
		get("/extractPhrases", (req, res) -> {
			String text = req.queryParams("text");
			return tokenizerResource.extractPhrasesGet(text);
		});
	}

}
