package org.openkoreantext;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        List<KoreanTokenJava> parsed =
            OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(
                OpenKoreanTextProcessorJava.tokenize("오픈코리안텍스트 한국어 분석기 API입니다"));
        return parsed.toString();
    }
}
