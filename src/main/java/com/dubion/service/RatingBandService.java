package com.dubion.service;



import com.dubion.domain.RatingBand;
import com.dubion.repository.RatingBandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.google.api.plus.moments.Rating;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing RatingArtist.
 */
@Service
@Transactional
public class RatingBandService {

    private final Logger log = LoggerFactory.getLogger(RatingBandService.class);

    private final RatingBandRepository ratingBandRepository;

    public RatingBandService(RatingBandRepository ratingBandRepository) {
        this.ratingBandRepository = ratingBandRepository;
    }
    public RatingBand save(RatingBand ratingBand) {
        log.debug("Request to save FavouriteSong : {}", ratingBand);
        return ratingBandRepository.save(ratingBand);
    }
    @Transactional(readOnly = true)
    public List<RatingBand> findAll() {
        log.debug("Request to get all FavouriteSong");
        return ratingBandRepository.findAll();
    }
    @Transactional(readOnly = true)
    public RatingBand findOne(Long id) {
        log.debug("Request to get FavouriteSong : {}", id);
        return ratingBandRepository.findOne(id);
    }
    public void delete(Long id) {
        log.debug("Request to delete FavouriteSong : {}", id);
        ratingBandRepository.delete(id);
    }
}
