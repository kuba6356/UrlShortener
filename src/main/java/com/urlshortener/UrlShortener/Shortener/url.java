package com.urlshortener.UrlShortener.Shortener;

public class url {
    private  Integer id;
    String url;
    String shortUrl;
    Encoder encoder;

    public url(Integer id, String url){
        this.id = id;
        this.url = url;
        this.shortUrl = encoder.encode(id);
    }
}

