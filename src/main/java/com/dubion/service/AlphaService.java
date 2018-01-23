package com.dubion.service;

import com.dubion.domain.Alpha;
import com.dubion.repository.AlphaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Alpha.
 */
@Service
@Transactional
public class AlphaService {

    private final Logger log = LoggerFactory.getLogger(AlphaService.class);

    private final AlphaRepository alphaRepository;

    public AlphaService(AlphaRepository alphaRepository) {
        this.alphaRepository = alphaRepository;
    }

    /**
     * Save a alpha.
     *
     * @param alpha the entity to save
     * @return the persisted entity
     */
    public Alpha save(Alpha alpha) {
        log.debug("Request to save Alpha : {}", alpha);
        return alphaRepository.save(alpha);
    }

    /**
     *  Get all the alphas.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Alpha> findAll() {
        log.debug("Request to get all Alphas");
        return alphaRepository.findAllWithEagerRelationships();
    }

    /**
     *  Get one alpha by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Alpha findOne(Long id) {
        log.debug("Request to get Alpha : {}", id);
        return alphaRepository.findOneWithEagerRelationships(id);
    }

    /**
     *  Delete the  alpha by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Alpha : {}", id);
        alphaRepository.delete(id);
    }
}
