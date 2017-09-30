package com.example.com.myapplication;

import retrofit2.http.GET;

//TODO this is wrong - Retrofit is not intended for downloads...
public interface CatApi {
    @GET("images")
    String get();
}
