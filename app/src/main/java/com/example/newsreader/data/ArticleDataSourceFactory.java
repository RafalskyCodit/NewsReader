package com.example.newsreader.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.newsreader.model.Article;
import com.example.newsreader.model.UsualQuery;

public class ArticleDataSourceFactory extends DataSource.Factory<Integer, Article> {

    private MutableLiveData<UsualArticleDataSource> usualDataSource;
    private UsualQuery query;

    public ArticleDataSourceFactory() {
        usualDataSource = new MutableLiveData<>();
        query = new UsualQuery();
    }

    @Override
    public DataSource<Integer, Article> create() {
        UsualArticleDataSource dataSource = new UsualArticleDataSource(query);
        this.usualDataSource.postValue(dataSource);
        return dataSource;

    }

    public MutableLiveData<UsualArticleDataSource> getUsualDataSource() {
        return usualDataSource;
    }

    public void updateUsualQuery(UsualQuery query) {
        this.query = query;
        usualDataSource.getValue().invalidate();
    }
}
