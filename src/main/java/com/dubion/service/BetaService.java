package com.dubion.service;

import com.dubion.domain.Beta;
import com.dubion.repository.BetaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Beta.
 */
@Service
@Transactional
public class BetaService {

    private final Logger log = LoggerFactory.getLogger(BetaService.class);

    private final BetaRepository betaRepository;

    public BetaService(BetaRepository betaRepository) {
        this.betaRepository = betaRepository;
    }

    /**
     * Save a beta.
     *
     * @param beta the entity to save
     * @return the persisted entity
     */
    public Beta save(Beta beta) {
        log.debug("Request to save Beta : {}", beta);
        return betaRepository.save(beta);
    }

    /**
     *  Get all the betas.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Beta> findAll() {
        log.debug("Request to get all Betas");
        return betaRepository.findAll();
    }

    /**
     *  Get one beta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Beta findOne(Long id) {
        log.debug("Request to get Beta : {}", id);
        return betaRepository.findOne(id);
    }

    /**
     *  Delete the  beta by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Beta : {}", id);
        betaRepository.delete(id);
    }
}
