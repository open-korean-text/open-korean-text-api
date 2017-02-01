package org.openkoreantext;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import org.json.simple.JSONObject;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("processor")
public class Processor {

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String processorPost(
      @DefaultValue("오픈코리안텍스트") @QueryParam("text") String text
  ) {
    JSONObject json = tokenizeJson(text);

    return json.toJSONString();
  }

  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public Response processorGet(
      @DefaultValue("오픈코리안텍스트") @QueryParam("text") String text
  ) {
    JSONObject json = tokenizeJson(text);

    return Response.status(201).entity( json.toJSONString()).build();
  }

  private JSONObject tokenizeJson(@DefaultValue("오픈코리안텍스트") @QueryParam("text") String text) {
    Seq<KoreanTokenizer.KoreanToken> parsed = OpenKoreanTextProcessorJava.tokenize(text);

    return new JSONObject(
        ImmutableMap.of(
            "tokens", OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(parsed),
            "token_strings", OpenKoreanTextProcessorJava.tokensToJavaStringList(parsed)
        ));
  }
}
