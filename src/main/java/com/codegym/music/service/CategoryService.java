package com.codegym.music.service;

import com.codegym.music.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();
}
