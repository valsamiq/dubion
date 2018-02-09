package com.dubion.repository;

import com.dubion.domain.Band;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

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
    Band findByNameContaining(String bandName);
}
