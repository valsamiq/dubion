package com.dubion.repository;

import com.dubion.domain.RatingSong;
import com.dubion.domain.Song;
import com.dubion.service.dto.StatsSongsRating;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the RatingSong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingSongRepository extends JpaRepository<RatingSong, Long> {

    @Query("select rating_song from RatingSong rating_song where rating_song.user.login = ?#{principal.username}")
    List<RatingSong> findByUserIsCurrentUser();

    @Query("select new com.dubion.service.dto.StatsSongsRating(rateSong.song," +
        "avg(rateSong.rating), max(rateSong.rating), min (rateSong.rating))" +
        "from RatingSong  rateSong where rateSong.song.id = :songId")
    StatsSongsRating findSongStats(@Param("songId")Long id);

    //@Query("select rateSong from RateSong where rateSong.user.login = :userLogin and rateSong.song = :song")
    Optional <RatingSong> findBySongAndUserLogin (Song song, String login);

}
