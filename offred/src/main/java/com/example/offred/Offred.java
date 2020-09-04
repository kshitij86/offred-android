package com.example.offred;

import android.util.Log;

public class Offred {
    private final String TAG = "OFFRED";
    public static Response response = new Response();

    // TODO: Make response specific to a method and not global

    public Response get(String endpoint) {
        new Thread(() -> {
            try {
                response = OffredUtil.makeGetRequest(endpoint);
            } catch (Exception e) {
                response.isException = true;
                Log.d(TAG, e.getMessage());
            }
        }).start();
        return response;
    }

    public Response post(String endpoint, String postData) {
        new Thread(() -> {
            try {
                response = OffredUtil.makePostRequest(endpoint, postData);
            } catch (Exception e) {
                response.isException = true;
                Log.d(TAG, e.getMessage());
            }
        }).start();
        return response;
    }
}