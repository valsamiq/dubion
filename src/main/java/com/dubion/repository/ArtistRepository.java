package com.dubion.repository;

import com.dubion.domain.Artist;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Artist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {
    @Query("select distinct artist from Artist artist left join fetch artist.bands")
    List<Artist> findAllWithEagerRelationships();

    @Query("select artist from Artist artist left join fetch artist.bands where artist.id =:id")
    Artist findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select a from Artist a where a.name=:name")
    Artist findByName(@Param("name")String name);

}
