package com.codegym.music.service;

import com.codegym.music.model.Singer;
import com.codegym.music.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SongService extends IService<Song>{
    Page<Song> findAllByNameContains(String name, Pageable pageable);

    Page<Song> findAll(Pageable pageable);
}

