package com.example.newsreader.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.newsreader.api.NewsApi;
import com.example.newsreader.data.ArticleDataSourceFactory;
import com.example.newsreader.data.TopLineheadersDataSource;
import com.example.newsreader.data.TopLineheadersDataSourceFactory;
import com.example.newsreader.data.UsualArticleDataSource;
import com.example.newsreader.model.Article;
import com.example.newsreader.model.TopHeadlinesQuery;
import com.example.newsreader.model.UsualQuery;

public class MainViewModel extends ViewModel {

    private LiveData<PagedList<Article>> pagedList;
    private MutableLiveData<UsualArticleDataSource> usualArticleDataSource;
    private MutableLiveData<TopLineheadersDataSource> topLineheadersDataSource;
    private ArticleDataSourceFactory usualFactory;
    private TopLineheadersDataSourceFactory topLineheadersFactory;
    private PagedList.Config config;


    public MainViewModel() {
        usualFactory = new ArticleDataSourceFactory();
        usualArticleDataSource = usualFactory.getUsualDataSource();

        topLineheadersFactory = new TopLineheadersDataSourceFactory();
        topLineheadersDataSource = topLineheadersFactory.getTopLineheadersDataSource();

        config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(NewsApi.PER_PAGE)
                .build();

        pagedList = new LivePagedListBuilder(usualFactory, config).build();
    }

    public MutableLiveData<UsualArticleDataSource> getUsualArticleDataSource() {
        return usualArticleDataSource;
    }

    public MutableLiveData<TopLineheadersDataSource> getTopLineheadersDataSource() {
        return topLineheadersDataSource;
    }

    public LiveData<PagedList<Article>> getPagedList() {
        return pagedList;
    }

    public void updateUsualQuery(UsualQuery query) {
        pagedList = new LivePagedListBuilder(usualFactory, config).build();
        usualFactory.updateUsualQuery(query);
    }

    public void updateHeadlinesQuery(TopHeadlinesQuery query) {
        pagedList = new LivePagedListBuilder(topLineheadersFactory, config).build();
        topLineheadersFactory.updateTopHeadlinesQuery(query);
    }
}
