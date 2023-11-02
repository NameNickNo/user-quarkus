package org.acme.repository;

import org.acme.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    void save(User user);

    void delete(User user);
}
