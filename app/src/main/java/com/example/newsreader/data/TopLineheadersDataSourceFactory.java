package com.example.newsreader.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.newsreader.model.Article;
import com.example.newsreader.model.TopHeadlinesQuery;

public class TopLineheadersDataSourceFactory extends DataSource.Factory<Integer, Article> {
    private MutableLiveData<TopLineheadersDataSource> topLineheadersDataSource;
    private TopHeadlinesQuery query;

    public TopLineheadersDataSourceFactory() {
        topLineheadersDataSource = new MutableLiveData<>();
        query = new TopHeadlinesQuery();
        topLineheadersDataSource.postValue(new TopLineheadersDataSource(query));
    }

    @Override
    public DataSource<Integer, Article> create() {
        TopLineheadersDataSource dataSource = new TopLineheadersDataSource(query);
        this.topLineheadersDataSource.postValue(dataSource);
        return dataSource;

    }

    public MutableLiveData<TopLineheadersDataSource> getTopLineheadersDataSource() {
        return topLineheadersDataSource;
    }

    public void updateTopHeadlinesQuery(TopHeadlinesQuery query) {
        this.query = query;
        topLineheadersDataSource.getValue().invalidate();
    }
}
