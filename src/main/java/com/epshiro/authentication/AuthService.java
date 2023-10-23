package com.epshiro.authentication;

import com.epshiro.entities.User;
import com.epshiro.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AuthService {
    private final UserService userService;

    public AuthService() {
        userService = new UserService();
    }
    public Optional<User> login(String email, String password) {
        Optional<User> userOptional = userService.findUserByEmail(email);

        if (userOptional.isPresent()) {
            if (BCrypt.checkpw(password, userOptional.get().getPassword()))
                return userOptional;
        }
        return Optional.empty();
    }

    public User registerUser(User user) throws IllegalArgumentException {
        validateUserDetails(user);
        user.setPassword(hashPassword(user.getPassword()));
        return userService.save(user);
    }

    private void validateUserDetails(User user) throws IllegalArgumentException {
        if (
                user.getFirstname() == null   || user.getFirstname().isEmpty() || user.getFirstname().isBlank()
                || user.getLastname() == null || user.getLastname().isEmpty()  || user.getLastname().isBlank()
                || user.getEmail().isBlank()  || user.getEmail().isEmpty()
                || user.getPassword().isBlank() || user.getPassword().isEmpty() || user.getPassword().length() < 6
        )
            throw new IllegalArgumentException("Invalid user data");

        if (userService.findUserByEmail(user.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exist");

    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
