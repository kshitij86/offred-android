package com.example.offred;

import android.util.Log;

import org.json.JSONException;

public class Offred extends Thread  {
    private final String TAG = "OFFRED";
    private String endpoint;
    public static Response response = new Response();


    Offred(String endpoint){
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        try {
            this.response = OffredUtil.makeGetRequest(endpoint);
        } catch (Exception e) {
            this.response.isException = true;
            Log.d(TAG, e.getMessage());
        }
    }

    public static Response get(String endpoint) {
        // execute() is non-static, needs an object to be called
        Offred offred = new Offred(endpoint);
        offred.start();
        return response;
    }
}