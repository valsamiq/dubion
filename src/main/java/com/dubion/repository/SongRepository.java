package com.dubion.repository;

import com.dubion.domain.Song;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Song entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select distinct song from Song song left join fetch song.albums")
    List<Song> findAllWithEagerRelationships();

    @Query("select song from Song song left join fetch song.albums where song.id =:id")
    Song findOneWithEagerRelationships(@Param("id") Long id);

}
