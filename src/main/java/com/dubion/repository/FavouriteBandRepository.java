package com.dubion.repository;

import com.dubion.domain.Band;
import com.dubion.domain.FavouriteBand;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the FavouriteBand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavouriteBandRepository extends JpaRepository<FavouriteBand, Long>,JpaSpecificationExecutor<FavouriteBand> {

    @Query("select favourite_band from FavouriteBand favourite_band where favourite_band.user.login = ?#{principal.username}")
    List<FavouriteBand> findByUserIsCurrentUser();


    FavouriteBand findByBandAndUserLogin(Band band, String login);
    Optional<FavouriteBand> findByBandIdAndUserLogin(Long bandId, String login);
}
