package com.dubion.service;

import com.dubion.domain.FavouriteAlbum;
import com.dubion.repository.FavouriteAlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing FavouriteAlbum.
 */
@Service
@Transactional
public class FavouriteAlbumService {

    private final Logger log = LoggerFactory.getLogger(FavouriteAlbumService.class);

    private final FavouriteAlbumRepository favouriteAlbumRepository;

    public FavouriteAlbumService(FavouriteAlbumRepository favouriteAlbumRepository) {
        this.favouriteAlbumRepository = favouriteAlbumRepository;
    }

    /**
     * Save a favouriteAlbum.
     *
     * @param favouriteAlbum the entity to save
     * @return the persisted entity
     */
    public FavouriteAlbum save(FavouriteAlbum favouriteAlbum) {
        log.debug("Request to save FavouriteAlbum : {}", favouriteAlbum);
        return favouriteAlbumRepository.save(favouriteAlbum);
    }

    /**
     *  Get all the favouriteAlbum.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FavouriteAlbum> findAll() {
        log.debug("Request to get all FavouriteBAlbum");
        return favouriteAlbumRepository.findAll();
    }

    /**
     *  Get one favouriteAlbum by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public FavouriteAlbum findOne(Long id) {
        log.debug("Request to get FavouriteAlbum : {}", id);
        return favouriteAlbumRepository.findOne(id);
    }

    /**
     *  Delete the  favouriteAlbum by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FavouriteAlbum : {}", id);
        favouriteAlbumRepository.delete(id);
    }
}
