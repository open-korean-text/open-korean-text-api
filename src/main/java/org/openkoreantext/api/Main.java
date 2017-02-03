package org.openkoreantext.api;

import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		new KoreanTextController();
	}
	
	private static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567;
	}

}
