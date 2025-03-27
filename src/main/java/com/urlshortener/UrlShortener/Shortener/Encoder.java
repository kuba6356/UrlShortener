package com.urlshortener.UrlShortener.Shortener;

public class Encoder {
    private final String Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public  String encode(Integer id){
      String shortUrl = "";
      while(id > 0){
          shortUrl = shortUrl + Characters.charAt(id%62);
          id/= 62;
      }
      return shortUrl;
    }
}

