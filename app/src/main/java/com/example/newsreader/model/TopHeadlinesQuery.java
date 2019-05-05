package com.example.newsreader.model;

public class TopHeadlinesQuery extends Query{
    private String category;
    private String country;

    public TopHeadlinesQuery(String query, String category, String country){
        super(query);
        this.category = category;
        this.country = country;
    }

    public TopHeadlinesQuery() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
