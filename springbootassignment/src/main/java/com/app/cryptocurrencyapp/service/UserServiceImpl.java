package com.app.cryptocurrencyapp.service;

/*Create UserServiceImpl which will implement UserService Interface. Implement every method from the interface
 * and also handle the exceptions.
 */
import com.app.cryptocurrencyapp.exceptions.UserAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.UserNotFoundException;
import com.app.cryptocurrencyapp.model.User;
import com.app.cryptocurrencyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> registerUser(User user) throws UserAlreadyExistException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User with email " + user.getEmail() + " already exists");
        }
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Boolean validateUser(String email, String password) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getPassword().equals(password);
        } else {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
    }
}

