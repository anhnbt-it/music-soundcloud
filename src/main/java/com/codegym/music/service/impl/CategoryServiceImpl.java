package com.codegym.music.service.impl;

import com.codegym.music.model.Category;
import com.codegym.music.repository.CategoryRepository;
import com.codegym.music.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
