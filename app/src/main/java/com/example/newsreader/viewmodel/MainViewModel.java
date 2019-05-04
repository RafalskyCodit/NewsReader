package com.example.newsreader.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.newsreader.api.NewsApi;
import com.example.newsreader.data.ArticleDataSourceFactory;
import com.example.newsreader.model.Article;

public class MainViewModel extends ViewModel {

    private LiveData<PagedList<Article>> pagedList;

    public MainViewModel(){
        ArticleDataSourceFactory factory = new ArticleDataSourceFactory();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(NewsApi.PER_PAGE)
                .build();

        pagedList = new LivePagedListBuilder(factory, config).build();
    }

    public LiveData<PagedList<Article>> getPagedList() {
        return pagedList;
    }
}
