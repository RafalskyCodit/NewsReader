package com.example.newsreader.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.newsreader.api.NewsApi;
import com.example.newsreader.model.Article;
import com.example.newsreader.model.RetrofitClient;
import com.example.newsreader.model.ServerResponse;
import com.example.newsreader.model.UsualQuery;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataSource extends PageKeyedDataSource<Integer, Article> {
    private NewsApi api;
    private UsualQuery query;

    public ArticleDataSource(UsualQuery query){
        api = RetrofitClient.getInstance().getNewsApi();
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Article> callback) {
        final int page = 1;
        api.getAllArticles(query.getQuery(), query.getFromDate(), query.getToDate(), query.getLanguage(), query.getSortBy(),
                page, NewsApi.PER_PAGE).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull retrofit2.Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        callback.onResult(serverResponse.getArticles(), 0, (int)serverResponse.getTotalResults(), null, page + 1);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
        api.getAllArticles(query.getQuery(), query.getFromDate(), query.getToDate(), query.getLanguage(), query.getSortBy(),
                params.key, NewsApi.PER_PAGE).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null) {
                        callback.onResult(serverResponse.getArticles(), key);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {


            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
        api.getAllArticles(query.getQuery(), query.getFromDate(), query.getToDate(), query.getLanguage(), query.getSortBy(),
                params.key, NewsApi.PER_PAGE).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    ServerResponse serverResponse = response.body();
                    if (serverResponse != null){
                        callback.onResult(serverResponse.getArticles(), params.key + 1);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
