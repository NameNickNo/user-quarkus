package com.project.user.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import com.project.user.exception.UserEmailNotUniqueException;
import com.project.user.exception.UserNotCreateException;
import com.project.user.model.User;
import com.project.user.service.UserService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserValidatorImpl implements UserValidator{

    @ConfigProperty(name = "user.error.msg.emailnotunique")
    String emailNotUnique;

    @Inject
    Validator validator;

    @Inject
    UserService userService;

    public void validate(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            String collect = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(" ; "));
            throw new UserNotCreateException(collect);
        }

        checkEmailUniqueness(user);
    }

    private void checkEmailUniqueness(User user) {
        if (userService.findByEmail(user.getEmail()).isPresent())
            throw new UserEmailNotUniqueException(emailNotUnique);
    }
}
