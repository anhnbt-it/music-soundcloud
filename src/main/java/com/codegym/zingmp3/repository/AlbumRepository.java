package com.codegym.zingmp3.repository;

import com.codegym.zingmp3.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {

    Page<Album> findAllByNameContains(String name, Pageable pageable);

    Page<Album> findAll(Pageable pageable);

    Iterable<Album> findAll();

}
