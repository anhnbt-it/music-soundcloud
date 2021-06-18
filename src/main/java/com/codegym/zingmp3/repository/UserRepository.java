package com.codegym.zingmp3.repository;

import com.codegym.zingmp3.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Page<Song> findAll(Pageable pageable);


}
