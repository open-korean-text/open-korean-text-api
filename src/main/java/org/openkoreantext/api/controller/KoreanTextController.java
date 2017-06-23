package org.openkoreantext.api.controller;

import org.openkoreantext.api.OpenKoreanTextHelper;

import spark.Request;
import spark.Response;
import spark.Route;

public class KoreanTextController {
    static OpenKoreanTextHelper tokenizerResource = new OpenKoreanTextHelper();
    final static String PARAM_TEXT = "text";
    
    public static Route normalize = (Request req, Response res) -> {
    	return tokenizerResource.normalize(req.queryParams(PARAM_TEXT));
    };
    
    public static Route tokenize = (Request req, Response res) -> {
    	return tokenizerResource.tokenize(req.queryParams(PARAM_TEXT));
    };
    
    public static Route extractPhrase = (Request req, Response res) -> {
    	return tokenizerResource.extractPhrase(req.queryParams(PARAM_TEXT));
    };
    
    public static Route normalizePost = (Request req, Response res) -> {
    	return tokenizerResource.normalize(req.body());
    };
    
    public static Route tokenizePost = (Request req, Response res) -> {
    	return tokenizerResource.tokenize(req.body());
    };
    
    public static Route extractPhrasePost = (Request req, Response res) -> {
    	return tokenizerResource.extractPhrase(req.body());
    };
}
