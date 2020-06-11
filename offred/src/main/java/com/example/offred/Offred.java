package com.example.offred;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Offred extends AsyncTask<String, Void, String> {
    private final String TAG = "OFFRED";

    @Override
    protected String doInBackground(String... urls) {
        String data = "";
        try {
            String urltoCall = urls[0];
            data = OffredUtil.makeRequest(urltoCall);
            return data;
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        return data;
    }

    public static String giveResponse(String link) throws ExecutionException, InterruptedException {
        Offred offred = new Offred();
        return offred.execute(link)
                .get();
    }
}
