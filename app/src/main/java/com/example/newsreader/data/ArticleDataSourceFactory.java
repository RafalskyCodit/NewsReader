package com.example.newsreader.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.newsreader.model.Article;
import com.example.newsreader.model.UsualQuery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArticleDataSourceFactory extends DataSource.Factory<Integer, Article> {

    private MutableLiveData<ArticleDataSource> dataSource;
    private UsualQuery query;

    public ArticleDataSourceFactory(){
        dataSource = new MutableLiveData<>();
        query = new UsualQuery("news", new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date()),
                    new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date()), "en", "publishedAt");
    }

    @Override
    public DataSource<Integer, Article> create() {
        ArticleDataSource dataSource = new ArticleDataSource(query);
        this.dataSource.postValue(dataSource);
        return dataSource;
    }

    public void updateQuery(UsualQuery query){
        this.query = query;
        dataSource.getValue().invalidate();
    }
}
