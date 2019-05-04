package com.example.newsreader.model;

import com.example.newsreader.api.NewsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsreader.api.NewsApi.BASE_URL;

public class RetrofitClient {
    private static RetrofitClient client;
    private NewsApi api;

    private RetrofitClient(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NewsApi.class);
    }

    public static RetrofitClient getInstance(){
        if (client == null){
            client = new RetrofitClient();
        }
        return client;
    }

    public NewsApi getNewsApi(){
        return api;
    }
}
