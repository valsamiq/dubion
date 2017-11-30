package com.dubion.repository;

import com.dubion.domain.RatingAlbum;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the RatingAlbum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingAlbumRepository extends JpaRepository<RatingAlbum, Long> {

    @Query("select rating_album from RatingAlbum rating_album where rating_album.user.login = ?#{principal.username}")
    List<RatingAlbum> findByUserIsCurrentUser();

}
