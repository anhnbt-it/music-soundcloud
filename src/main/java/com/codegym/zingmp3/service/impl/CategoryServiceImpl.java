package com.codegym.zingmp3.service.impl;

import com.codegym.zingmp3.model.Genre;
import com.codegym.zingmp3.repository.CategoryRepository;
import com.codegym.zingmp3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Genre> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return categoryRepository.findAll(paging);
    }

    @Override
    public Page<Genre> findAllByNameContains(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return categoryRepository.findAllByNameContains(name, paging);
    }

    @Override
    public Iterable<Genre> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return categoryRepository.save(genre);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
