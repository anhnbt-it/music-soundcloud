package com.codegym.music.service;

import com.codegym.music.model.Song;

public interface SongService {
    Iterable<Song> findAll();
}

