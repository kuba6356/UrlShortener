package com.urlshortener.UrlShortener.Users;

import com.urlshortener.UrlShortener.Shortener.Url;
import com.urlshortener.UrlShortener.Shortener.UrlRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    static List<User> users = new ArrayList<>();
    public static void Register(String email, String userName, String password){
        User user = new User(email, userName, password);
        users.add(user);
    }
    public static List<User> getAllUsers() {
        return new ArrayList<>(users); // Return a copy to prevent modification
    }
    public static Boolean Login(String emailOrUserName, String password){
        User loginUser = users.stream()
                .filter(user ->user.getEmail().equals(emailOrUserName) || user.getUserName().equals(emailOrUserName))
                .findFirst()
                .orElse(null);
        if(loginUser == null){
            return null;
        }
        if(((loginUser.getEmail().equals(emailOrUserName)) ||
                (loginUser.getUserName().equals(emailOrUserName))) &&
                new BCryptPasswordEncoder().matches(password, loginUser.getPassword())){
            UrlRepo.setLoggedUser(loginUser);
            return true;
        }
        else{
            return false;
        }
    }

}
