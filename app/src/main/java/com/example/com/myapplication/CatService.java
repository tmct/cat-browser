package com.example.com.myapplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class CatService {
    private static final String API_URL = "http://thecatapi.com/api/";

    private Retrofit getCatService() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    CatApi getCatApi() {
        return getCatService().create(CatApi.class);
    }
}
