package com.app.cryptocurrencyapp.controller;

import com.app.cryptocurrencyapp.exceptions.UserAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.UserNotFoundException;
import com.app.cryptocurrencyapp.model.User;
import com.app.cryptocurrencyapp.service.UserService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class is a REST controller for handling user-related requests.
 * It provides methods for creating, reading, updating, and deleting user data.
 */
//Add cross origin for localhost:5173
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${app.jwt.secret}")
    private String secret;
    

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.registerUser(user).get());
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity validateUser(@RequestBody User user) {
        try {
            boolean isValidUser = userService.validateUser(user.getEmail(), user.getPassword());
            if (isValidUser) {
                String token = generateJwtToken(user.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*Write a private method to generate the JWT token using added dependencies in build.gradle file
     * and the secret key from application.properties file.
     * The method should take email as input and should return the JWT token.
     * Use the JwtBuilder class from the io
     * 
     */

private String generateJwtToken(String email) {
    try {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .setIssuer("cryptocurrency-app")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    } catch (Exception e) {
       System.out.println(e);
         return null;
    }
}

}
