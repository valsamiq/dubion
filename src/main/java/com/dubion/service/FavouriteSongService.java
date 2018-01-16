package com.dubion.service;

import com.dubion.domain.Band;
import com.dubion.domain.FavouriteSong;
import com.dubion.repository.BandRepository;
import com.dubion.repository.FavouriteSongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Band.
 */
@Service
@Transactional

public class FavouriteSongService {

    private final Logger log = LoggerFactory.getLogger(FavouriteSongService.class);

    private final FavouriteSongRepository favouriteSongRepository;

    public FavouriteSongService(FavouriteSongRepository favouriteSongRepository) {
        this.favouriteSongRepository = favouriteSongRepository;
    }
    public FavouriteSong save(FavouriteSong favouriteSong) {
        log.debug("Request to save FavouriteSong : {}", favouriteSong);
        return favouriteSongRepository.save(favouriteSong);
    }
    @Transactional(readOnly = true)
    public List<FavouriteSong> findAll() {
        log.debug("Request to get all FavouriteSong");
        return favouriteSongRepository.findAll();
    }
    @Transactional(readOnly = true)
    public FavouriteSong findOne(Long id) {
        log.debug("Request to get FavouriteSong : {}", id);
        return favouriteSongRepository.findOne(id);
    }
    public void delete(Long id) {
        log.debug("Request to delete FavouriteSong : {}", id);
        favouriteSongRepository.delete(id);
    }
}
