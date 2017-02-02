package org.openkoreantext.api;

import static spark.Spark.*;

public class Main {
	
	static TokenizerResource tokenizerResource = new TokenizerResource();

	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		
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
	
	private static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567;
	}

}
