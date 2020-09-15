package com.example.offred;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// TODO: Update UI for multiple request demos
// TODO: Test delete method

public class OffredUtil {
    private static final String TAG = "OFFRED";

    /* GET from an endpoint */
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
            response.resBody = new JSONObject(res);
            response.headers = conn.getHeaderFields();

            // Release the resource
            conn.disconnect();
        } catch(Exception e) {
            response.isException = true;
        }

        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }

    /* POST to an endpoint */
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
            response.resBody = new JSONObject(resp.toString());
            response.headers = conn.getHeaderFields();

            conn.disconnect();
        } catch (Exception e){
            response.isException = true;
        }

        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }

    /* DELETE from an endpoint */
    public static Response makeDeleteRequest(String endpoint){
        double start = System.currentTimeMillis();
        Response response = new Response();
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);


            // Perform delete and disconnect
            conn.connect();

            // Set response headers
            response.headers = conn.getHeaderFields();

            conn.disconnect();
        } catch (Exception e){
            response.isException = true;
        }
        double end = System.currentTimeMillis();
        response.time = (end - start) / 1000;

        return response;
    }
}
