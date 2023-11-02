package com.project.user.service;

import com.project.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(long id);

    void save(User user);

    void remove(long id);

    void update(long id, User user);

    Optional<User> findByEmail(String email);
}
