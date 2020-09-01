package com.example.offred;

import android.util.Log;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class OffredUtil {
    private static final String TAG = "OFFRED";

    public static Response makeGetRequest(String GET_URL) throws IOException, NullPointerException{
        double start = System.currentTimeMillis();
        Response response = new Response();
        try {
            /* Set up url connection */
            URL url = new URL(GET_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Set HTTP request method

            /* Handle response */
            String res = "";
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ipLine;
            while((ipLine = r.readLine()) != null){
                res += (ipLine);
            }
            response.statusCode = String.valueOf(conn.getResponseCode());
            response.resBody = res;
        } catch(UnknownHostException e) {
            Log.d(TAG, e.getMessage());
        }

        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }
}
