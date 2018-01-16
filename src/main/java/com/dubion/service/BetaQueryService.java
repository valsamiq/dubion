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

import com.dubion.domain.Beta;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.BetaRepository;
import com.dubion.service.dto.BetaCriteria;


/**
 * Service for executing complex queries for Beta entities in the database.
 * The main input is a {@link BetaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Beta} or a {@link Page} of {%link Beta} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class BetaQueryService extends QueryService<Beta> {

    private final Logger log = LoggerFactory.getLogger(BetaQueryService.class);


    private final BetaRepository betaRepository;

    public BetaQueryService(BetaRepository betaRepository) {
        this.betaRepository = betaRepository;
    }

    /**
     * Return a {@link List} of {%link Beta} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Beta> findByCriteria(BetaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Beta> specification = createSpecification(criteria);
        return betaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Beta} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Beta> findByCriteria(BetaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Beta> specification = createSpecification(criteria);
        return betaRepository.findAll(specification, page);
    }

    /**
     * Function to convert BetaCriteria to a {@link Specifications}
     */
    private Specifications<Beta> createSpecification(BetaCriteria criteria) {
        Specifications<Beta> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Beta_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Beta_.name));
            }
        }
        return specification;
    }

}
