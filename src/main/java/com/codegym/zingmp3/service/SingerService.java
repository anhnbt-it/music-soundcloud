package com.codegym.zingmp3.service;


import com.codegym.zingmp3.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingerService extends IService<Artist> {

    Page<Artist> findAllByNameContains(String name, Pageable pageable);

    Page<Artist> findAll(Pageable pageable);
}
