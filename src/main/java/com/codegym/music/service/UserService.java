package com.codegym.music.service;

import com.codegym.music.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);

    User findByEmail(String email);

    Iterable<User> findAll();

    Optional<User> findById(Long id);
}
