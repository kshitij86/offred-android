package com.example.offred;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public class Response {

    public Double time = 0.0;
    Map<String, List<String>> headers = null;
    public String statusCode = "404";
    public JSONObject resBody;
    public Boolean isException = false;

    Response(){}
}
