package com.dubion.repository;

import com.dubion.domain.RatingBand;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the RatingBand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingBandRepository extends JpaRepository<RatingBand, Long> {

    @Query("select rating_band from RatingBand rating_band where rating_band.user.login = ?#{principal.username}")
    List<RatingBand> findByUserIsCurrentUser();

}
