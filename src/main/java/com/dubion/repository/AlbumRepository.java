package com.dubion.repository;

import com.dubion.domain.Album;
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


    List<Album> findByNameContaining(String name);
}
