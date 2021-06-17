package com.codegym.zingmp3.service.impl;


import com.codegym.zingmp3.model.Album;
import com.codegym.zingmp3.repository.AlbumRepository;
import com.codegym.zingmp3.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Page<Album> findAllByNameContains(String name, Pageable pageable) {
        return albumRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Page<Album> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return albumRepository.findAll(paging);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album save(Album entity) {
        return albumRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public long count() {
        return albumRepository.count();
    }
}
