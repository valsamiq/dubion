package com.dubion.service;

import com.dubion.domain.Label;
import com.dubion.repository.LabelRepository;
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
public class LabelService {

    private final Logger log = LoggerFactory.getLogger(LabelService.class);

    private final LabelRepository albumRepository;

    public LabelService(LabelRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    /**
     * Save a Label.
     *
     * @param album the entity to save
     * @return the persisted entity
     */
    public Label save(Label album) {
        log.debug("Reques to save Label : {}", album);
        return albumRepository.save(album);
    }
    /**
     *  Get all the bands.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Label> findAll() {
        log.debug("Request to get all Labels");
        return albumRepository.findAll();
    }

    /**
     *  Get one album by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Label findOne(Long id){
        log.debug("Requesto to get Label : {}", id);
        return albumRepository.findOne(id);
    }
    /**
     *  Delete the  album by id.
     *
     *  @param id the id of the entity
     */

    public void delete(Long id){
        log.debug("Request to delete Label : {}", id);
        albumRepository.delete(id);
    }
}
