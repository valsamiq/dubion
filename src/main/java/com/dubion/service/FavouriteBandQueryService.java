package com.dubion.service;

import com.dubion.domain.Band_;
import com.dubion.domain.FavouriteBand;
import com.dubion.domain.FavouriteBand_;
import com.dubion.domain.User_;
import com.dubion.repository.FavouriteBandRepository;
import com.dubion.service.dto.FavouriteBandCriteria;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service for executing complex queries for FavouriteBand entities in the database.
 * The main input is a {@link FavouriteBandCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link FavouriteBand} or a {@link Page} of {%link FavouriteBand} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class FavouriteBandQueryService extends QueryService<FavouriteBand> {

    private final Logger log = LoggerFactory.getLogger(FavouriteBandQueryService.class);


    private final FavouriteBandRepository favouritebandRepository;

    public FavouriteBandQueryService(FavouriteBandRepository favouritebandRepository) {
        this.favouritebandRepository = favouritebandRepository;
    }

    /**
     * Return a {@link List} of {%link FavouriteBand} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FavouriteBand> findByCriteria(FavouriteBandCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FavouriteBand> specification = createSpecification(criteria);
        return favouritebandRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link FavouriteBand} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FavouriteBand> findByCriteria(FavouriteBandCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FavouriteBand> specification = createSpecification(criteria);
        return favouritebandRepository.findAll(specification, page);
    }

    /**
     * Function to convert FavouriteBandCriteria to a {@link Specifications}
     */
    private Specifications<FavouriteBand> createSpecification(FavouriteBandCriteria criteria) {
        Specifications<FavouriteBand> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FavouriteBand_.id));
            }
            if (criteria.getLiked() != null) {
                specification = specification.and(buildSpecification(criteria.getLiked(), FavouriteBand_.liked));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), FavouriteBand_.date));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserId(), FavouriteBand_.user, User_.id));
            }
            if (criteria.getBandId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBandId(), FavouriteBand_.band, Band_.id));
            }
        }
        return specification;
    }

}
