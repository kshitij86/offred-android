package com.example.offred;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class Offred {
    public static Response response = new Response();

    // TODO: Make response specific to a method and not global

    public Future<Response> get(String endpoint) {
        return CompletableFuture.supplyAsync(() -> OffredUtil.makeGetRequest(endpoint));
    }

    public Future<Response> post(String endpoint, String postData) {
        return CompletableFuture.supplyAsync(() -> OffredUtil.makePostRequest(endpoint, postData));
    }

    public void delete (String endpoint) {
        Executor deleteExec = delete -> OffredUtil.makeDeleteRequest(endpoint);
    }
}