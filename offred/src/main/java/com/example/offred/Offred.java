package com.example.offred;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Offred {
    private final String TAG = "OFFRED";
    public static Response response = new Response();

    // TODO: Make response specific to a method and not global

    public Future<Response> get(String endpoint) throws IOException {
        return CompletableFuture.supplyAsync(() -> OffredUtil.makeGetRequest(endpoint));
    }

    public Future<Response> post(String endpoint, String postData) throws IOException {
        return CompletableFuture.supplyAsync(() -> OffredUtil.makePostRequest(endpoint, postData));
    }
}