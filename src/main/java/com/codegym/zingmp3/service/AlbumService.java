package com.codegym.zingmp3.service;


import com.codegym.zingmp3.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlbumService extends IService<Album> {

    Page<Album> findAllByNameContains(String name, Pageable pageable);

    Page<Album> findAll(Pageable pageable);

    Optional<Album> findByNameContains(String name);

}
