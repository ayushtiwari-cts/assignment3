package com.app.cryptocurrencyapp.service;

import com.app.cryptocurrencyapp.exceptions.UserAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.UserNotFoundException;
import com.app.cryptocurrencyapp.model.User;
import java.util.Optional;


public interface UserService {
    /**
     * This method should take user object as input and should return Optional added User object only.
     *
     * @param user the user object to be registered
     * @return an Optional containing the added User object
     * @throws UserAlreadyExistException 
     */
    Optional<User> registerUser(User user) throws UserAlreadyExistException;

    /**
     * This method should take email and pa     * This method should take email and password as input and should return Optional User object only.
 return Optional User object only.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return True if the user is valid, False otherwise
     * @throws UserNotFoundException 
     */
    Boolean validateUser(String email, String password) throws UserNotFoundException;
}
