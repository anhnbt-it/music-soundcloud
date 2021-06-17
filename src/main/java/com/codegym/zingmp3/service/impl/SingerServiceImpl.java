package com.codegym.zingmp3.service.impl;


import com.codegym.zingmp3.model.Artist;
import com.codegym.zingmp3.service.SingerService;
import com.codegym.zingmp3.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerRepository singerRepository;

    @Override
    public Iterable<Artist> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Page<Artist> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        return singerRepository.findAll(paging);
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Artist save(Artist entity) {
        return singerRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public long count() {
        return singerRepository.count();
    }

    @Override
    public Page<Artist> findAllByNameContains(String name, Pageable pageable) {

        return singerRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public Page<Artist> findAll(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }
}
