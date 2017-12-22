package com.dubion.service;

import com.dubion.domain.BandPrueba;
import com.dubion.repository.BandPruebaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing BandPrueba.
 */
@Service
@Transactional
public class BandPruebaService {

    private final Logger log = LoggerFactory.getLogger(BandPruebaService.class);

    private final BandPruebaRepository bandPruebaRepository;

    public BandPruebaService(BandPruebaRepository bandPruebaRepository) {
        this.bandPruebaRepository = bandPruebaRepository;
    }

    /**
     * Save a bandPrueba.
     *
     * @param bandPrueba the entity to save
     * @return the persisted entity
     */
    public BandPrueba save(BandPrueba bandPrueba) {
        log.debug("Request to save BandPrueba : {}", bandPrueba);
        return bandPruebaRepository.save(bandPrueba);
    }

    /**
     *  Get all the bandPruebas.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<BandPrueba> findAll() {
        log.debug("Request to get all BandPruebas");
        return bandPruebaRepository.findAll();
    }

    /**
     *  Get one bandPrueba by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public BandPrueba findOne(Long id) {
        log.debug("Request to get BandPrueba : {}", id);
        return bandPruebaRepository.findOne(id);
    }

    /**
     *  Delete the  bandPrueba by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete BandPrueba : {}", id);
        bandPruebaRepository.delete(id);
    }
}
