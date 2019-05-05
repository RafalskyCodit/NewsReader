package com.example.newsreader.api;

import com.example.newsreader.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    String BASE_URL = "https://newsapi.org/v2/";
    int PER_PAGE = 10;

    @GET("everything?apiKey=6dcde639227b4e1bb7bf93257e6f9695")
    Call<ServerResponse> getAllArticles(@Query("q") String query,
                                        @Query(value = "from", encoded = true) String fromDate,
                                        @Query(value = "to", encoded = true) String toDate,
                                        @Query("language") String language,
                                        @Query("sortBy") String sortBy,
                                        @Query("page") int page,
                                        @Query("pageSize") int pageSize);

    @GET("top-headlines?apiKey=6dcde639227b4e1bb7bf93257e6f9695")
    Call<ServerResponse> getTopHeadlines(@Query("q") String query,
                                         @Query("country") String country,
                                         @Query("category") String category,
                                         @Query("page") int page,
                                         @Query("pageSize") int pageSize);

}
