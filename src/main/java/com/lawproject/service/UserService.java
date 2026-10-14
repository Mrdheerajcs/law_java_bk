package com.lawproject.service;

import com.lawproject.dto.AuthRequest;
import com.lawproject.dto.UserReq;
import com.lawproject.entity.User;
import com.lawproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(AuthRequest authRequest) {
        Optional<User> user = userRepository.findByEmail(authRequest.getUsername());
        if (user.isPresent()) {
            return passwordEncoder.matches(authRequest.getPassword(), user.get().getPassword());
        }
        return false;
    }

    public User createUser(UserReq userReq) {
        if (userRepository.existsByEmail(userReq.getEmail())) {
            return null;
        }
        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        return userRepository.save(user);
    }

    public boolean forgotPassword(String email, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}