package com.dubion.service;

import com.dubion.domain.Album;
import com.dubion.repository.AlbumRepository;
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
public class AlbumService {

    private final Logger log = LoggerFactory.getLogger(AlbumService.class);

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    /**
     * Save a Album.
     *
     * @param album the entity to save
     * @return the persisted entity
     */

    public Album save(Album album) {
        log.debug("Reques to save Album : {}", album);
        return albumRepository.save(album);
    }
    /**
     *  Get all the bands.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Album> findAll() {
        log.debug("Request to get all Albums");
        return albumRepository.findAll();
    }

    /**
     *  Get one album by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Album findOne(Long id){
        log.debug("Requesto to get Album : {}", id);
        return albumRepository.findOne(id);
    }
    /**
     *  Delete the  album by id.
     *
     *  @param id the id of the entity
     */

    public void delete(Long id){
        log.debug("Request to delete Album : {}", id);
        albumRepository.delete(id);
    }
}
