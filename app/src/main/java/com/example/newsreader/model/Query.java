package com.example.newsreader.model;

public class Query {
    private String query;

    public Query(String query) {
        this.query = query;
    }

    public Query() {

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
