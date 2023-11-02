package org.acme.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.acme.exception.UserEmailNotUniqueException;
import org.acme.exception.UserNotCreateException;
import org.acme.model.User;
import org.acme.service.UserService;
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

        if (userService.findByEmail(user.getEmail()).isPresent())
            throw new UserEmailNotUniqueException(emailNotUnique);
    }
}
