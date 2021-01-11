package com.codegym.music.repository;
import com.codegym.music.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    Page<Song> findAllByNameContains(String name, Pageable pageable);

    Page<Song> findAll(Pageable pageable);

    Iterable<Song> findAll();

}
