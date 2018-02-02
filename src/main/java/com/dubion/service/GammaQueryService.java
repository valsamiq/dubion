package com.dubion.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.dubion.domain.Gamma;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.GammaRepository;
import com.dubion.service.dto.GammaCriteria;


/**
 * Service for executing complex queries for Gamma entities in the database.
 * The main input is a {@link GammaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Gamma} or a {@link Page} of {%link Gamma} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class GammaQueryService extends QueryService<Gamma> {

    private final Logger log = LoggerFactory.getLogger(GammaQueryService.class);


    private final GammaRepository gammaRepository;

    public GammaQueryService(GammaRepository gammaRepository) {
        this.gammaRepository = gammaRepository;
    }

    /**
     * Return a {@link List} of {%link Gamma} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Gamma> findByCriteria(GammaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Gamma> specification = createSpecification(criteria);
        return gammaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Gamma} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Gamma> findByCriteria(GammaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Gamma> specification = createSpecification(criteria);
        return gammaRepository.findAll(specification, page);
    }

    /**
     * Function to convert GammaCriteria to a {@link Specifications}
     */
    private Specifications<Gamma> createSpecification(GammaCriteria criteria) {
        Specifications<Gamma> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Gamma_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Gamma_.name));
            }
            if (criteria.getAlbumId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAlbumId(), Gamma_.album, Album_.id));
            }
            if (criteria.getAlbumName() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAlbumName(), Gamma_.album, Album_.name));
            }
        }
        return specification;
    }

}
