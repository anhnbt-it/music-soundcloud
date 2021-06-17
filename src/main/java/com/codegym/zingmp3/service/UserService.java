package com.codegym.zingmp3.service;

import com.codegym.zingmp3.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);

    Optional<User> findByEmail(String email);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    boolean existByEmail(String email);
}
