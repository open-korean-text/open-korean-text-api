package org.openkoreantext.api;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;
import spark.utils.StringUtils;

public class KoreanTextControllerIntegrationTest {
    final private String NORMALIZE = "{\"strings\":\"오픈코리안텍스트\"}";
    final private String TOKENIZE = "{\"tokens\":[\"오픈(Noun: 0, 2)\",\"코리안(Noun: 2, 3)\",\"텍스트(Noun: 5, 3)\"],\"token_strings\":[\"오픈\"," +
        "\"코리안\",\"텍스트\"]}";
    final private String EXTRACT = "{\"phrases\":[\"오픈코리안텍스트(Noun: 0, 8)\",\"오픈(Noun: 0, 2)\",\"코리안(Noun: 2, 3)\",\"텍스트(Noun: 5, 3)\"]}";
    final private String TEXT = "오픈코리안텍스트";

    @BeforeClass
    public static void beforeClass() {
        Main.main(null);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void normalizeGetTest() {
        Map<String, Object> response = makeRequest("GET", "/normalize?text=" + TEXT, null);
        assertMessage(response, NORMALIZE);
        response = makeRequest("POST", "/normalize", TEXT);
        assertMessage(response, NORMALIZE);
    }

    @Test
    public void tokenizeGetTest() {
        Map<String, Object> response = makeRequest("GET", "/tokenize?text=" + TEXT, null);
        assertMessage(response, TOKENIZE);
        response = makeRequest("POST", "/tokenize?text", TEXT);
        assertMessage(response, TOKENIZE);
    }

    @Test
    public void extractPhrasesTest() {
        Map<String, Object> response = makeRequest("GET", "/extractPhrases?text=" + TEXT, null);
        assertMessage(response, EXTRACT);
        response = makeRequest("POST", "/extractPhrases", TEXT);
        assertMessage(response, EXTRACT);
    }

    private void assertMessage(Map<String, Object> response, String expected) {
        assertEquals(200, response.get("status"));
        assertEquals(expected, response.get("body"));
    }

    private Map<String, Object> makeRequest(String method, String path, String data) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if (StringUtils.isNotEmpty(data)) {
                connection.getOutputStream()
                          .write(data.getBytes());
            }
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            Map<String, Object> request = new HashMap<String, Object>();
            request.put("status", connection.getResponseCode());
            request.put("body", body);
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
