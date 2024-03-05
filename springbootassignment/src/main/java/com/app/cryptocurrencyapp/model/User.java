/**
 * Represents a user in the cryptocurrency application.
 */
package com.app.cryptocurrencyapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String email;
    private String username;
    private String password;
    private String mobile;
}




