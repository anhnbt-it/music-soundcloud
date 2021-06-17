package com.codegym.zingmp3.repository;


import com.codegym.zingmp3.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends PagingAndSortingRepository<Artist, Long> {

    Page<Artist> findAllByNameContains(String name, Pageable pageable);

    Page<Artist> findAll(Pageable pageable);

    Iterable<Artist> findAll();

}
