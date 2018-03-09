package com.dubion.repository;

import com.dubion.domain.Album;
import com.dubion.domain.Song;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Album entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {
    @Query("select distinct album from Album album left join fetch album.genres")
    List<Album> findAllWithEagerRelationships();

    @Query("select album from Album album left join fetch album.genres where album.id =:id")
    Album findOneWithEagerRelationships(@Param("id") Long id);

    //@Query("select album from Album album left join fetch album.band where album.name=:name")
    Album findByNameContaining(String artistName);

    @Query("select a from Album a where a.name=:name")
    Album findByName(@Param("name")String name);

}
