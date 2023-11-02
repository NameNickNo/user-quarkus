package com.project.user.service;

import com.project.user.exception.UserNotFoundException;
import com.project.user.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import com.project.user.repository.UserRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {


    @ConfigProperty(name = "user.error.msg.usernotfound")
    String userNotFound;

    @Inject
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void remove(long id) {
        User user = findById(id).orElseThrow(() -> new UserNotFoundException(String.format(userNotFound, id)));
        userRepository.delete(user);
    }

    @Transactional
    public void update(long id, User updatedUser) {
        User userToUpdate = findById(id).orElseThrow(() -> new UserNotFoundException(String.format(userNotFound, id)));
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPassword(updatedUser.getPassword());

        userRepository.save(userToUpdate);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

