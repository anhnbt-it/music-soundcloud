package com.codegym.music.service.impl;

import com.codegym.music.model.Song;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongService songService;


    @Override
    public Page<Song> findAll(Integer pageNo, Integer pageSize, String sortBy) {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return songService.findAll(pageNo, pageSize, sortBy);
    }

    @Override
    public Page<Song> findAllByNameContains(String name, Integer pageNo, Integer pageSize, String sortBy) {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return songService.findAllByNameContains(name, pageNo, pageSize, sortBy);
    }

    @Override
    public Iterable<Song> findAll() {
        return songService.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songService.findById(id);
    }

    @Override
    public Song save(Song song) {
        return songService.save(song);
    }

    @Override
    public void deleteById(Long id) {
        songService.deleteById(id);
    }
}
