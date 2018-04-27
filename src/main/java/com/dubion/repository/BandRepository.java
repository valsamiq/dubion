package com.dubion.repository;

import com.dubion.domain.Band;
import com.dubion.domain.Genre;
import com.dubion.domain.Song;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the Band entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BandRepository extends JpaRepository<Band, Long>, JpaSpecificationExecutor<Band> {
    @Query("select distinct band from Band band left join fetch band.genres")
    List<Band> findAllWithEagerRelationships();

    @Query("select band from Band band left join fetch band.genres where band.id =:id")
    Band findOneWithEagerRelationships(@Param("id") Long id);

    //@Query("select band from Band band left join fetch band. where song.name=:name")
    @Query("select a from Band a where a.name=:name")
    Band findByName(@Param("name")String name);

    List<Band> findByNameContaining(String name);
}
