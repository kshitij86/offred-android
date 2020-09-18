package com.example.offred;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public class Response {

    Double time = 0.0;
    Map<String, List<String>> headers = null;
    int statusCode;
    JSONObject resBody;
    boolean isException = false;
    Exception requestError;

    Response(){}
}
