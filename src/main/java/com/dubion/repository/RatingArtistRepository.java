package com.dubion.repository;

import com.dubion.domain.RatingArtist;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the RatingArtist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingArtistRepository extends JpaRepository<RatingArtist, Long> {

    @Query("select rating_artist from RatingArtist rating_artist where rating_artist.user.login = ?#{principal.username}")
    List<RatingArtist> findByUserIsCurrentUser();

}
