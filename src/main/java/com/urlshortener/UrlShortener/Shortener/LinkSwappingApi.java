package com.urlshortener.UrlShortener.Shortener;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static com.urlshortener.UrlShortener.Shortener.UrlRepo.urlSwapper;
@Controller
@RestController
public class LinkSwappingApi {
    @GetMapping("/api/{shortUrl}")
    public RedirectView shortToLong (@PathVariable String shortUrl){
        try {
            return new RedirectView(urlSwapper(shortUrl));
        } catch (NullPointerException e ){
            return new RedirectView("404");
        }
    }
}
