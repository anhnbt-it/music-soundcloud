package com.codegym.music.service.impl;

import com.codegym.music.model.Song;
import com.codegym.music.repository.SongRepository;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }
}
