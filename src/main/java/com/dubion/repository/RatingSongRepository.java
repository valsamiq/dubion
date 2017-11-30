package com.dubion.repository;

import com.dubion.domain.RatingSong;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the RatingSong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingSongRepository extends JpaRepository<RatingSong, Long> {

    @Query("select rating_song from RatingSong rating_song where rating_song.user.login = ?#{principal.username}")
    List<RatingSong> findByUserIsCurrentUser();

}
