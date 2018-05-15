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

import com.dubion.domain.Band;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.BandRepository;
import com.dubion.service.dto.BandCriteria;

import com.dubion.domain.enumeration.Status;

/**
 * Service for executing complex queries for Band entities in the database.
 * The main input is a {@link BandCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Band} or a {@link Page} of {%link Band} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class BandQueryService extends QueryService<Band> {

    private final Logger log = LoggerFactory.getLogger(BandQueryService.class);


    private final BandRepository bandRepository;

    public BandQueryService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    /**
     * Return a {@link List} of {%link Band} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Band> findByCriteria(BandCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Band> specification = createSpecification(criteria);
        return bandRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Band} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Band> findByCriteria(BandCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Band> specification = createSpecification(criteria);
        return bandRepository.findAll(specification, page);
    }

    /**
     * Function to convert BandCriteria to a {@link Specifications}
     */
    private Specifications<Band> createSpecification(BandCriteria criteria) {
        Specifications<Band> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Band_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Band_.name));
            }
            if (criteria.getBirthDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthDate(), Band_.birthDate));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), Band_.bio));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Band_.status));
            }
            if (criteria.getPhoto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoto(), Band_.photo));
            }
            if (criteria.getNapsterId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNapsterId(), Band_.napsterId));
            }
            if (criteria.getCountryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCountryId(), Band_.country, Country_.id));
            }
            if (criteria.getLabelId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getLabelId(), Band_.label, Label_.id));
            }
            if (criteria.getSocialId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSocialId(), Band_.social, Social_.id));
            }
            if (criteria.getGenreId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGenreId(), Band_.genres, Genre_.id));
            }
            if (criteria.getRatingId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRatingId(), Band_.ratings, RatingBand_.id));
            }
            if (criteria.getFavouriteId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFavouriteId(), Band_.favourites, FavouriteBand_.id));
            }
            if (criteria.getArtistId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getArtistId(), Band_.artists, Artist_.id));
            }
        }
        return specification;
    }

}
