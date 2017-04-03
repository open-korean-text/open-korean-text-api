package org.openkoreantext.api;

import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		
		//String origin = "https://open-korean-text-web.firebaseapp.com";
		String origin = "*";
		String method = "GET";
		String headers = "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,";
		enableCORS(origin, method, headers);
		
		new KoreanTextController();
	}

	private static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567;
	}

	// Enables CORS on requests. This method is an initialization method and
	// should be called once.
	private static void enableCORS(final String origin, final String methods, final String headers) {

		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", origin);
			response.header("Access-Control-Request-Method", methods);
			response.header("Access-Control-Allow-Headers", headers);
			// Note: this may or may not be necessary in your particular
			// application
			response.type("application/json; charset=UTF-8");
		});
	}
}
