package com.dubion.repository;

import com.dubion.domain.Genre;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the Genre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select a from Genre a where a.name=:name")
    Genre findByName(@Param("name")String name);

    @Query("select a from Genre a where a.name=:name")
    Set<Genre> findByNames(@Param("name")String name);

}
