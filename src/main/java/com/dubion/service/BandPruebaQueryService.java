package com.dubion.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.dubion.domain.BandPrueba;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.BandPruebaRepository;
import com.dubion.service.dto.BandPruebaCriteria;


/**
 * Service for executing complex queries for BandPrueba entities in the database.
 * The main input is a {@link BandPruebaCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link BandPrueba} or a {@link Page} of {%link BandPrueba} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class BandPruebaQueryService extends QueryService<BandPrueba> {

    private final Logger log = LoggerFactory.getLogger(BandPruebaQueryService.class);


    private final BandPruebaRepository bandPruebaRepository;

    public BandPruebaQueryService(BandPruebaRepository bandPruebaRepository) {
        this.bandPruebaRepository = bandPruebaRepository;
    }

    /**
     * Return a {@link List} of {%link BandPrueba} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BandPrueba> findByCriteria(BandPruebaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<BandPrueba> specification = createSpecification(criteria);
        return bandPruebaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link BandPrueba} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BandPrueba> findByCriteria(BandPruebaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<BandPrueba> specification = createSpecification(criteria);
        return bandPruebaRepository.findAll(specification, page);
    }

    /**
     * Function to convert BandPruebaCriteria to a {@link Specifications}
     */
    private Specifications<BandPrueba> createSpecification(BandPruebaCriteria criteria) {
        Specifications<BandPrueba> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BandPrueba_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), BandPrueba_.name));
            }
            if (criteria.getBirthdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthdate(), BandPrueba_.birthdate));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), BandPrueba_.bio));
            }
        }
        return specification;
    }

}
