package com.urlshortener.UrlShortener.Shortener;

import com.urlshortener.UrlShortener.Users.User;

import java.util.ArrayList;
import java.util.List;

public class UrlRepo {
    public static User loggedUser;

    public static void setLoggedUser(User loggedUser) {
        UrlRepo.loggedUser = loggedUser;
    }

    public static void create(String link) {
        Url newUrl = new Url(link);
        loggedUser.linki.add(newUrl);
    }

    public static List<Url> getAllUrls() {
        return loggedUser.getLinki();
    }

    public static Url getUrlById(String shortUrl) {
        return loggedUser.getLinki().stream()
                .filter(url -> url.getShortUrl().equals(shortUrl))
                .findFirst()
                .orElse(null);
    }
    public static String urlSwapper(String shortUrl){
                Url link = loggedUser.getLinki().stream()
                .filter(url -> url.getShortUrl().equals(shortUrl))
                .findFirst()
                .orElse(null);
                if(link == null){
                    return null;
                }
                return link.getLink();
    }
    public static void urlChanger(String newUrl, String shortUrl){
        Url link = loggedUser.getLinki().stream()
                .filter( url -> url.getShortUrl().equals(shortUrl))
                .findFirst()
                .orElse(null);
        link.setLink(newUrl);
    }
    public static void urlDeleter(String shortUrl){
        Url link = loggedUser.getLinki().stream()
                .filter( url -> url.getShortUrl().equals(shortUrl))
                .findFirst()
                .orElse(null);
        loggedUser.linki.remove(link);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}