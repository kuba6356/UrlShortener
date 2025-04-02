package com.urlshortener.UrlShortener.Shortener;

import com.urlshortener.UrlShortener.Users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.urlshortener.UrlShortener.Shortener.UrlRepo.urlChanger;
import static com.urlshortener.UrlShortener.Shortener.UrlRepo.urlDeleter;

@RestController
@RequestMapping("/api/url")
public class ShortenerRestController {

    @GetMapping
    public ResponseEntity<List<Url>> getAllLinks() {
        try {
            List<Url> urls = UrlRepo.getAllUrls();
            return ResponseEntity.ok(urls);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/single")
    public ResponseEntity<Url> getLinkById(@RequestParam String shortUrl) {
        try {
            Url url = UrlRepo.getUrlById(shortUrl);
            if (url == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(url);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/Create")
    public ResponseEntity<String> createNewLink(@RequestParam String link) {
        try {
            if (link == null || link.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            UrlRepo.create(link);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e){
            return new ResponseEntity<>("you need to login first",HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/Change")
    public ResponseEntity<String> changeUrl(@RequestParam String shortUrl, @RequestParam String newLink){
        try {
            urlChanger(newLink, shortUrl);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/Delete")
    public ResponseEntity<String> deleteUrl(@RequestParam String shortUrl){
        try {
            urlDeleter(shortUrl);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("loggedUser")
    public ResponseEntity<User> getLoggedUser(){
        try {
            return new ResponseEntity<>(UrlRepo.getLoggedUser(), HttpStatus.OK);

        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}