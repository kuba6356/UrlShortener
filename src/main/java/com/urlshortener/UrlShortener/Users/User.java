package com.urlshortener.UrlShortener.Users;

import com.urlshortener.UrlShortener.Shortener.Url;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


public class User {
    String Email;
    String Password;
    String UserName;
    public ArrayList<Url> linki = new ArrayList<>();

    public User(String email, String userName, String password){
        this.Email = email;
        this.UserName = userName;
        this.Password = new BCryptPasswordEncoder(5).encode(password);
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }

    public ArrayList<Url> getLinki() {
        return linki;
    }

}
