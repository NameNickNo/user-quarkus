package org.acme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;

    @NotNull(message = "Attribute 'name' can not be null!")
    @Pattern(regexp = "[A-zА-я]+", message = "Attribute 'name' must contain only letters!")
    @Size(max = 100, message = "Attribute 'name' must be less than 100 symbols")
    private String name;

    @NotNull(message = "Attribute 'email' can not be null!")
    @Email(message = "Attribute 'email' not correct!")
    @Size(max = 100, message = "Attribute 'email' must be less than 100 symbols")
    private String email;

    @NotNull(message = "Attribute 'password' can not be null!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{3,}",
            message = "Attribute 'password' must contain: character, uppercase character and number")
    @Size(min = 8, max = 100, message = "Attribute 'password' must be greater than 8 and less than 100 symbols")
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
