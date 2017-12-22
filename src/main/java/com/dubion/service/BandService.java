package com.dubion.service;

import com.dubion.domain.Band;
import com.dubion.repository.BandRepository;
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
public class BandService {

    private final Logger log = LoggerFactory.getLogger(BandService.class);

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    /**
     * Save a band.
     *
     * @param band the entity to save
     * @return the persisted entity
     */
    public Band save(Band band) {
        log.debug("Request to save Band : {}", band);
        return bandRepository.save(band);
    }

    /**
     *  Get all the bands.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Band> findAll() {
        log.debug("Request to get all Bands");
        return bandRepository.findAll();
    }

    /**
     *  Get one band by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Band findOne(Long id) {
        log.debug("Request to get Band : {}", id);
        return bandRepository.findOne(id);
    }

    /**
     *  Delete the  band by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Band : {}", id);
        bandRepository.delete(id);
    }
}
