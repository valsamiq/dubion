package com.dubion.repository;

import com.dubion.domain.Band;
import com.dubion.domain.RatingBand;
import com.dubion.service.dto.StatsBandRating;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the RatingBand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingBandRepository extends JpaRepository<RatingBand, Long>, JpaSpecificationExecutor<RatingBand> {

    @Query("select rating_band from RatingBand rating_band where rating_band.user.login = ?#{principal.username}")
    List<RatingBand> findByUserIsCurrentUser();

    @Query("select new com.dubion.service.dto.StatsBandRating(ratingBand.band, max(ratingBand.rating), min(ratingBand.rating), avg(ratingBand.rating))" +
        "from RatingBand ratingBand where ratingBand.band.id = :bandId")
    StatsBandRating findBandStats(@Param("bandId") Long id);

    Optional<RatingBand> findByBandAndUserLogin(Band band, String login);

}
