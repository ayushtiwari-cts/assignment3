package com.app.cryptocurrencyapp.repository;

import com.app.cryptocurrencyapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The UserRepository interface is responsible for providing CRUD operations for the User entity in the MongoDB database.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds a user by their email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return an Optional containing the user if found, or an empty Optional if not found
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found, or an empty Optional if not found
     */
    Optional<User> findByEmail(String email);
}
