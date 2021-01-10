package com.codegym.music.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface SongService<E> {
    List<E> findAll();
    E findById(Long id);
    E save(E obj);
    void delete(Long id);
}

