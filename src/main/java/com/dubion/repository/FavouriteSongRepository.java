package com.dubion.repository;

import com.dubion.domain.FavouriteSong;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the FavouriteSong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavouriteSongRepository extends JpaRepository<FavouriteSong, Long> {

    @Query("select favourite_song from FavouriteSong favourite_song where favourite_song.user.login = ?#{principal.username}")
    List<FavouriteSong> findByUserIsCurrentUser();

}
