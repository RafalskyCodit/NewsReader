package com.example.newsreader.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UsualQuery extends Query{
    private String fromDate;
    private String toDate;
    private String language;
    private String sortBy;

    public UsualQuery(String query, String fromDate, String toDate, String language, String sortBy) {
        super(query);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.language = language;
        this.sortBy = sortBy;
    }

    public UsualQuery() {
        setQuery("news");
        fromDate = new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date());
        toDate = new SimpleDateFormat("yy-MM-dd", Locale.getDefault()).format(new Date());
        language = "en";
        sortBy = "publishedAt";
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
