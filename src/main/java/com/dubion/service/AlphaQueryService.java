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

import com.dubion.domain.Alpha;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.AlphaRepository;
import com.dubion.service.dto.AlphaCriteria;


/**
 * Service for executing complex queries for Alpha entities in the database.
 * The main input is a {@link AlphaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Alpha} or a {@link Page} of {%link Alpha} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class AlphaQueryService extends QueryService<Alpha> {

    private final Logger log = LoggerFactory.getLogger(AlphaQueryService.class);


    private final AlphaRepository alphaRepository;

    public AlphaQueryService(AlphaRepository alphaRepository) {
        this.alphaRepository = alphaRepository;
    }

    /**
     * Return a {@link List} of {%link Alpha} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Alpha> findByCriteria(AlphaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Alpha> specification = createSpecification(criteria);
        return alphaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Alpha} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Alpha> findByCriteria(AlphaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Alpha> specification = createSpecification(criteria);
        return alphaRepository.findAll(specification, page);
    }

    /**
     * Function to convert AlphaCriteria to a {@link Specifications}
     */
    private Specifications<Alpha> createSpecification(AlphaCriteria criteria) {
        Specifications<Alpha> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Alpha_.id));
            }
            if (criteria.getEdad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEdad(), Alpha_.edad));
            }
            if (criteria.getBetaId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBetaId(), Alpha_.betas, Beta_.id));
            }

            if (criteria.getBetaName() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBetaName(), Alpha_.betas, Beta_.name));
            }
        }
        return specification;
    }

}
