package com.dubion.service;

import com.dubion.domain.Gamma;
import com.dubion.repository.GammaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Gamma.
 */
@Service
@Transactional
public class GammaService {

    private final Logger log = LoggerFactory.getLogger(GammaService.class);

    private final GammaRepository gammaRepository;

    public GammaService(GammaRepository gammaRepository) {
        this.gammaRepository = gammaRepository;
    }

    /**
     * Save a gamma.
     *
     * @param gamma the entity to save
     * @return the persisted entity
     */
    public Gamma save(Gamma gamma) {
        log.debug("Request to save Gamma : {}", gamma);
        return gammaRepository.save(gamma);
    }

    /**
     *  Get all the gammas.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Gamma> findAll() {
        log.debug("Request to get all Gammas");
        return gammaRepository.findAll();
    }

    /**
     *  Get one gamma by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Gamma findOne(Long id) {
        log.debug("Request to get Gamma : {}", id);
        return gammaRepository.findOne(id);
    }

    /**
     *  Delete the  gamma by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Gamma : {}", id);
        gammaRepository.delete(id);
    }
}
