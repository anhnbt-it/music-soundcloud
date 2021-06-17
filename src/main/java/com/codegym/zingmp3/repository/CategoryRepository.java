package com.codegym.zingmp3.repository;

import com.codegym.zingmp3.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Genre, Long> {

    Page<Genre> findAllByNameContains(String name, Pageable pageable);

}
