package com.example.offred;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OffredUtil {
    private static final String TAG = "OFFRED";

    public static Response makeGetRequest(String GET_URL)  {
        // TODO: Get more precise time
        double start = System.currentTimeMillis();
        Response response = new Response();
        try {
            /* Set up url connection */
            URL url = new URL(GET_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Set HTTP request method

            /* Package the response into an object */
            String res = "";
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ipLine;
            while((ipLine = r.readLine()) != null){
                res += (ipLine);
            }
            response.statusCode = String.valueOf(conn.getResponseCode());
            response.resBody = res;
        } catch(Exception e) {
            Log.d(TAG, e.getMessage());
        }

        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }

    public static Response makePostRequest(String POST_URL, String requestBody) {
        double start = System.currentTimeMillis();

        Response response = new Response();

        try{
            URL url = new URL(POST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Write the request to the connection
            OutputStream os = conn.getOutputStream();
            byte[] req = requestBody.getBytes("utf-8");
            os.write(req);

            // Read the response, if any
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder resp = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                resp.append(responseLine.trim());
            }
            response.statusCode = String.valueOf(conn.getResponseCode());
            response.resBody = resp.toString();
        } catch (Exception e){
            response.isException = true;
        }

        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }
}
