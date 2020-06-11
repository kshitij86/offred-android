package com.example.offred;

import android.util.Log;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OffredUtil {
    private static OkHttpClient client =  new OkHttpClient();
    private static  final String TAG = "OFFRED";
    private static final String INCOMPLETE_REQUEST = "INCOMPLETE_REQUEST";
    private static final String NULL_REQUEST = "NULL_REQUEST";
    private static final String EMPTY_URL = "EMPTY_URL";

    public static String makeRequest(String url){
        if(!url.matches("")){
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try(Response response = client.newCall(request).execute()){
                try{
                    return response.body().string();
                } catch (NullPointerException e){
                    return NULL_REQUEST;
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            return INCOMPLETE_REQUEST;
        }
        else {
            return EMPTY_URL;
        }
    }
}
