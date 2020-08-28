package com.example.offred;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.*;
import java.net.*;

public class OffredUtil {
    private static OkHttpClient client =  new OkHttpClient();
    private static  final String TAG = "OFFRED";
    private static final String NULL_REQUEST = "NULL_REQUEST";
    private static final String EMPTY_URL = "EMPTY_URL";
    private static String time = "";
    private static String status_code = "404";
    private static String res = NULL_REQUEST;

    public static String getResponseTime(){
        return time;
    }

    public static String getStatusCode(){
        return status_code;
    }

    public static String getResponseBody(){
        return res;
    }

    public static void makeGetRequest(String GET_URL) throws IOException, NullPointerException{
        double start = System.currentTimeMillis();
        try {
            /* Set up url connection */
            URL url = new URL(GET_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Set HTTP request method

            /* Handle response */
            res = "";
            status_code = String.valueOf(conn.getResponseCode());
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ipLine;
            while((ipLine = r.readLine()) != null){
                res += (ipLine);
            }
        } catch(UnknownHostException e) {
            Log.d(TAG, e.getMessage());
        }

        double end = System.currentTimeMillis();
        time = (end - start) / 1000 + "s";
    }
}
