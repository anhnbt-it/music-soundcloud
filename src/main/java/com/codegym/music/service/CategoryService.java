package com.codegym.music.service;

import com.codegym.music.model.Blog;
import com.codegym.music.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Page<Category> findAllByNameContains(String name, Pageable pageable);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void deleteById(Long id);

}
