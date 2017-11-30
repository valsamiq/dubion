package com.dubion.repository;

import com.dubion.domain.FavouriteBand;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the FavouriteBand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavouriteBandRepository extends JpaRepository<FavouriteBand, Long> {

    @Query("select favourite_band from FavouriteBand favourite_band where favourite_band.user.login = ?#{principal.username}")
    List<FavouriteBand> findByUserIsCurrentUser();

}
