package com.codegym.zingmp3.service;

import com.codegym.zingmp3.model.Genre;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoryService {

    Page<Genre> findAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<Genre> findAllByNameContains(String name, Integer pageNo, Integer pageSize, String sortBy);

    Iterable<Genre> findAll();

    Optional<Genre> findById(Long id);

    Genre save(Genre genre);

    void deleteById(Long id);

}
