package com.urlshortener.UrlShortener.Users;

import com.urlshortener.UrlShortener.Shortener.UrlRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.urlshortener.UrlShortener.Users.UserRepo.getAllUsers;

@RestController
@RequestMapping("/users/")
public class UserRestController {

    @GetMapping("login")
    public ResponseEntity<String> userLogin(@RequestParam String emailOrUsername, @RequestParam String Password){
        try {
            if( UserRepo.Login(emailOrUsername, Password) == true){
                return new ResponseEntity<>("Succesfully logged in",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("register")
    public ResponseEntity<User> userRegister(@RequestParam String email, @RequestParam String username, @RequestParam String password){
        try{
            UserRepo.Register(email, username, password);
            User newUser = UserRepo
                    .users
                    .stream()
                    .filter(user -> user.getEmail() == email)
                    .findFirst().orElse(null);
            if(email.equals("") || username.equals("") || password.equals("")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<String> getAllUsersData(){
        try {

            return new ResponseEntity( getAllUsers(),HttpStatus.OK);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
