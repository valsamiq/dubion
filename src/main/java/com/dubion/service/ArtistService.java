package com.dubion.service;

import com.dubion.domain.Artist;
import com.dubion.repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Artist.
 */
@Service
@Transactional
public class ArtistService {

    private final Logger log = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /** - Artist -
     * Save a artist.
     *
     * @param artist the entity to save
     * @return the persisted entity
     */
    public Artist save(Artist artist) {
        log.debug("Request to save Artist : {}", artist);
        return artistRepository.save(artist);
    }

    /**
     *  Get all the artist.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        log.debug("Request to get all Artist");
        return artistRepository.findAll();
    }

    /**
     *  Get one artist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Artist findOne(Long id) {
        log.debug("Request to get Artist : {}", id);
        return artistRepository.findOne(id);
    }

    /**
     *  Delete the  artist by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Artist : {}", id);
        artistRepository.delete(id);
    }
}
