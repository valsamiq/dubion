package com.dubion.service;

import com.dubion.domain.FavouriteBand;
import com.dubion.repository.FavouriteBandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing FavouriteBand.
 */
@Service
@Transactional
public class FavouriteBandService {

    private final Logger log = LoggerFactory.getLogger(FavouriteBandService.class);

    private final FavouriteBandRepository favouritebandRepository;

    public FavouriteBandService(FavouriteBandRepository favouritebandRepository) {
        this.favouritebandRepository = favouritebandRepository;
    }

    /**
     * Save a favouriteband.
     *
     * @param favouriteband the entity to save
     * @return the persisted entity
     */
    public FavouriteBand save(FavouriteBand favouriteband) {
        log.debug("Request to save FavouriteBand : {}", favouriteband);
        return favouritebandRepository.save(favouriteband);
    }

    /**
     *  Get all the favouritebands.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FavouriteBand> findAll() {
        log.debug("Request to get all FavouriteBands");
        return favouritebandRepository.findAll();
    }

    /**
     *  Get one favouriteband by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public FavouriteBand findOne(Long id) {
        log.debug("Request to get FavouriteBand : {}", id);
        return favouritebandRepository.findOne(id);
    }

    /**
     *  Delete the  favouriteband by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FavouriteBand : {}", id);
        favouritebandRepository.delete(id);
    }
}
