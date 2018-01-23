package com.dubion.service;


import com.dubion.domain.FavouriteSong;
import com.dubion.domain.RatingArtist;
import com.dubion.repository.FavouriteSongRepository;
import com.dubion.repository.RatingArtistRepository;
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
public class RatingArtistService {

    private final Logger log = LoggerFactory.getLogger(FavouriteSongService.class);

    private final RatingArtistRepository ratingArtistRepository;

    public RatingArtistService(RatingArtistRepository ratingArtistRepository) {
        this.ratingArtistRepository = ratingArtistRepository;
    }
    public RatingArtist save(RatingArtist ratingArtist) {
        log.debug("Request to save FavouriteSong : {}", ratingArtist);
        return ratingArtistRepository.save(ratingArtist);
    }
    @Transactional(readOnly = true)
    public List<RatingArtist> findAll() {
        log.debug("Request to get all FavouriteSong");
        return ratingArtistRepository.findAll();
    }
    @Transactional(readOnly = true)
    public RatingArtist findOne(Long id) {
        log.debug("Request to get FavouriteSong : {}", id);
        return ratingArtistRepository.findOne(id);
    }
    public void delete(Long id) {
        log.debug("Request to delete FavouriteSong : {}", id);
        ratingArtistRepository.delete(id);
    }
}
