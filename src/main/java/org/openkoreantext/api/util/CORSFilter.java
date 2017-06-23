package org.openkoreantext.api.util;

import static spark.Spark.before;
import static spark.Spark.options;

public class CORSFilter {
	
	static final String ORIGIN = "*";
	static final String METHOD = "GET";
	static final String HEADERS = "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,";
	
	// Enables CORS on requests. This method is an initialization method and
	// should be called once.
	public static void enable() {

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
			response.header("Access-Control-Allow-Origin", ORIGIN);
			response.header("Access-Control-Request-Method", METHOD);
			response.header("Access-Control-Allow-Headers", HEADERS);
			// Note: this may or may not be necessary in your particular
			// application
			response.type("application/json; charset=UTF-8");
		});
	}

}
