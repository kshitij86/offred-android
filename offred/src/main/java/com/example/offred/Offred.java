package com.example.offred;

import android.util.Log;

import org.json.JSONException;

public class Offred {
    private final String TAG = "OFFRED";
    public static Response response = new Response();

    // TODO: Make response specific to a method and not global

    public Response get(String endpoint) {
        new Thread(() -> {
            try {
                this.response = OffredUtil.makeGetRequest(endpoint);
            } catch (Exception e) {
                this.response.isException = true;
                Log.d(TAG, e.getMessage());
            }
        }).start();
        return this.response;
    }

    public Response post(String endpoint, String postData) {
        new Thread(() -> {
            try {
                this.response = OffredUtil.makePostRequest(endpoint, postData);
            } catch (Exception e) {
                this.response.isException = true;
                Log.d(TAG, e.getMessage());
            }
        }).start();
        return this.response;
    }
}