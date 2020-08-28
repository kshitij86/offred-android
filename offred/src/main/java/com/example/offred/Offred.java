package com.example.offred;

import android.os.AsyncTask;
import java.util.concurrent.ExecutionException;

public class Offred extends AsyncTask<String, Void, String> {
    private final String TAG = "OFFRED";

    @Override
    protected String doInBackground(String... urls) {
        try {
            OffredUtil.makeGetRequest(urls[0]);
        } catch (Exception e) {
            return OffredUtil.getResponseBody();
        }
        return OffredUtil.getResponseBody();
    }

    public static String giveResponse(String link) throws ExecutionException, InterruptedException {
        Offred offred = new Offred();
        return offred.execute(link).get();
    }
}