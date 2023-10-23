package com.epshiro.service;

import com.epshiro.dao.UserRepository;
import com.epshiro.entities.User;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User save(User user) {
        if(!isValidationUserDetails()){
            return null;
        }
        return userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        if(!isEmailValide(email))
            return Optional.empty();

        return userRepository.findUserByEmail(email);
    }

    private boolean isEmailValide(String email) {
        // TODO: implement the logics
        return true;
    }

    private boolean isValidationUserDetails() {
        // TODO: implement the validation logics
        return true;
    }
}
