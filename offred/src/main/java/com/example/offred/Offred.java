package com.example.offred;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class Offred extends AsyncTask<String, Void, Response> {
    private final String TAG = "OFFRED";

    @Override
    protected Response doInBackground(String... urls) {
        Response response = new Response();
        try {
            response = OffredUtil.makeGetRequest(urls[0]);
        } catch (Exception e) {
            response.isException = true;
            Log.d(TAG, e.getMessage());
        }
        return response;
    }

    public static Response giveResponse(String endpoint) throws ExecutionException, InterruptedException {
        // execute is non-static, needs an object to be called
        Offred offred = new Offred();
        return offred.execute(endpoint).get();
    }
}