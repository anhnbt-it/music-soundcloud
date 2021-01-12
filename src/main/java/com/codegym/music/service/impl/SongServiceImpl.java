package com.codegym.music.service.impl;

import com.codegym.music.model.Category;
import com.codegym.music.model.Song;
import com.codegym.music.repository.SongRepository;
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
    private SongRepository songRepository;


    @Override
    public Page<Song> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return songRepository.findAll(paging);
    }

    @Override
    public Page<Song> findAllByNameContains(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return songRepository.findAllByNameContains(name, paging);
    }

    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Iterable<Song> findAllBySingerId(Long id) {
        return songRepository.findAllBySingerId(id);
    }

    @Override
    public Iterable<Song> findAllBy5BySingerId(Long singer_id ,Long id){
        return songRepository.findFirst5BySingerIdAndIdNot(singer_id,id);
    }

    @Override
    public Page<Song> findAll( Pageable pageable) {
        return songRepository.findAll(pageable);
    }
}
