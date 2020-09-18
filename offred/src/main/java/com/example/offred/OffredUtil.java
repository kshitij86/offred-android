package com.example.offred;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// TODO: Update UI for multiple request demos
// TODO: Test delete method

public class OffredUtil {
    /* GET from an endpoint */
    protected static Response makeGetRequest(String GET_URL)  {
        StopWatch stopWatch = new StopWatch();
        Response response = new Response();

        stopWatch.setStartTime();
        try {
            /* Set up url connection */
            URL url = new URL(GET_URL); // Throws MalformedHttpUrlException
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET"); // Set HTTP request method

            /* Package the response into an object */
            StringBuilder res = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
            String ipLine;
            while((ipLine = r.readLine()) != null){
                res.append(ipLine);
            }
            response.statusCode = getConnection.getResponseCode();
            response.resBody = new JSONObject(res.toString());
            response.headers = getConnection.getHeaderFields();

            // Release the resource
            getConnection.disconnect();
        } catch(Exception e) {
            response.isException = true;
            response.requestError = e;
        }
        stopWatch.setEndTime();
        response.time = stopWatch.getTotalTime();

        return response;
    }

    /* POST to an endpoint */
    protected static Response makePostRequest(String POST_URL, String requestBody) {

        StopWatch stopWatch = new StopWatch();
        Response response = new Response();

        stopWatch.setStartTime();
        try{
            URL url = new URL(POST_URL);
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
            postConnection.setRequestMethod("POST");
            // Indicates writing to the resource
            postConnection.setDoOutput(true);

            // Write the request to the connection
            OutputStream os = postConnection.getOutputStream();
            byte[] req = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(req);

            // Read the response, if any
            BufferedReader br = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                sb.append(responseLine.trim());
            }
            response.statusCode = postConnection.getResponseCode();
            response.resBody = new JSONObject(sb.toString()); // String -> JSON Object
            response.headers = postConnection.getHeaderFields();

            postConnection.disconnect();
        } catch (Exception e){
            response.isException = true;
            response.requestError = e;
        }

        stopWatch.setEndTime();
        response.time = stopWatch.getTotalTime();

        return response;
    }

    /* DELETE from an endpoint */
    protected static Response makeDeleteRequest(String endpoint){
       StopWatch stopWatch = new StopWatch();
        Response response = new Response();

        stopWatch.setStartTime();
        try {
            URL url = new URL(endpoint);
            HttpURLConnection deleteConnection = (HttpURLConnection) url.openConnection();
            deleteConnection.setRequestMethod("DELETE");

            // Perform delete and disconnect
            deleteConnection.connect();

            // Set response headers
            response.headers = deleteConnection.getHeaderFields();
            response.statusCode = deleteConnection.getResponseCode();

            deleteConnection.disconnect();
        } catch (IOException e){
            response.isException = true;
            response.requestError = e;
        }
        stopWatch.setEndTime();
        response.time = stopWatch.getTotalTime();
        return response;
    }

    private static class StopWatch {
        private double startTime, totalTime, endTime;

        StopWatch(){
            this.startTime = this.totalTime = this.endTime = 0.0;
        }

        protected void setStartTime(){
            this.startTime = System.nanoTime();
        }

        protected void setEndTime(){
            this.endTime = System.nanoTime();
            int NANO_DIGIT = 1000000000;
            this.totalTime = (this.endTime - this.startTime) / NANO_DIGIT;
        }

        protected double getTotalTime(){
            return this.totalTime;
        }
    }
}
