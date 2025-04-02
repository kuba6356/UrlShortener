package com.urlshortener.UrlShortener.Shortener;

public class Url {
    private static Integer counter = 0;
    private Integer id = 0;
    private String link;
    private String shortUrl;
    public Url( String link) {
        counter++;
        this.link = link;
        this.shortUrl = new Encoder().encode(counter);
        this.id = counter;
    }

    // Required getters for JSON serialization
    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setLink(String link) {
        this.link = link;
    }
}