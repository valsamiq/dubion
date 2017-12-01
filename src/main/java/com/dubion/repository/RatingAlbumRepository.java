package com.dubion.repository;

import com.dubion.domain.Album;
import com.dubion.domain.RatingAlbum;
import com.dubion.service.dto.StatsAlbumRating;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the RatingAlbum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingAlbumRepository extends JpaRepository<RatingAlbum, Long> {

    @Query("select rating_album from RatingAlbum rating_album where rating_album.user.login = ?#{principal.username}")
    List<RatingAlbum> findByUserIsCurrentUser();

    @Query("select new com.dubion.service.dto.StatsAlbumRating(rateAlbum.album," +
        " avg(rateAlbum.rating), max(rateAlbum.rating), min(rateAlbum.rating))" +
        "from RatingAlbum rateAlbum where rateAlbum.album.id = albumId")
    StatsAlbumRating findAlbumStats(@Param("albumId") Long id);

    //@Query("select rateAlbum fom RateAlbum where rateAlbum.user.login = :userLogin and rate Album.album = :album")
    Optional<RatingAlbum> findByAlbumAndUserLogin(Album album, String login);
}
