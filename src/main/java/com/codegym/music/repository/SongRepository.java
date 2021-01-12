package com.codegym.music.repository;
import com.codegym.music.model.Category;
import com.codegym.music.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
    Page<Song> findAllByNameContains(String name, Pageable pageable);

    Iterable<Song> findFirst5BySingerIdAndIdNot(Long singer_id, Long id);

    Page<Song> findAll(Pageable pageable);

    Iterable<Song> findAllBySingerId(Long id);


}
