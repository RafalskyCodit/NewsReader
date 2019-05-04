package com.example.newsreader.model;

public class UsualQuery {
    private String query;
    private String fromDate;
    private String toDate;
    private String language;
    private String sortBy;

    public UsualQuery(String query, String fromDate, String toDate, String language, String sortBy) {
        this.query = query;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.language = language;
        this.sortBy = sortBy;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
