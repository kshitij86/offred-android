package com.example.offred;

public class Response {
    public final String NULL_REQUEST = "NULL_REQUEST";
    private static final String EMPTY_URL = "EMPTY_URL";

    public Double time = 0.0;
    public String statusCode = "404";
    public String resBody = NULL_REQUEST;
    public Boolean isException = false;

    Response(){}
}
