package org.openkoreantext;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;

import static org.junit.Assert.assertEquals;

public class TokenizerResourceTest extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(TokenizerResource.class);
  }

  /**
   * Test to see that the message "Got it!" is sent in the response.
   */
  @Test
  public void testTokenizerGet() {
    final String responseMsg = target().path("tokenize").request().get(String.class);

    assertEquals(
        "{\"tokens\":[오픈(ProperNoun: 0, 2),코리안(ProperNoun: 2, 3),텍스트(Noun: 5, 3)]," +
            "\"token_strings\":[\"오픈\",\"코리안\",\"텍스트\"]}",
        responseMsg);
  }

  @Test
  public void testTokenizerGetWithParams() {
    final String responseMsg = target()
        .path("tokenize")
        .queryParam("text", "한국어처리기입니다.")
        .request()
        .get(String.class);

    assertEquals(
        "{\"tokens\":[한국어(Noun: 0, 3),처리기(ProperNoun: 3, 3),입니(Adjective: 6, 2)," +
            "다(Eomi: 8, 1),.(Punctuation: 9, 1)]," +
            "\"token_strings\":[\"한국어\",\"처리기\",\"입니\",\"다\",\".\"]}",
        responseMsg);
  }
}
