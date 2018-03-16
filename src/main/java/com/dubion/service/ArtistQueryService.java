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

import com.dubion.domain.Artist;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.ArtistRepository;
import com.dubion.service.dto.ArtistCriteria;


/**
 * Service for executing complex queries for Artist entities in the database.
 * The main input is a {@link ArtistCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Artist} or a {@link Page} of {%link Artist} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class ArtistQueryService extends QueryService<Artist> {

    private final Logger log = LoggerFactory.getLogger(ArtistQueryService.class);


    private final ArtistRepository artistRepository;

    public ArtistQueryService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /**
     * Return a {@link List} of {%link Artist} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Artist> findByCriteria(ArtistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Artist> specification = createSpecification(criteria);
        return artistRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Artist} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Artist> findByCriteria(ArtistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Artist> specification = createSpecification(criteria);
        return artistRepository.findAll(specification, page);
    }

    /**
     * Function to convert ArtistCriteria to a {@link Specifications}
     */
    private Specifications<Artist> createSpecification(ArtistCriteria criteria) {
        Specifications<Artist> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Artist_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Artist_.name));
            }
            if (criteria.getBirthDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthDate(), Artist_.birthDate));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), Artist_.bio));
            }
            if (criteria.getPhoto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoto(), Artist_.photo));
            }
            if (criteria.getBandId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBandId(), Artist_.bands, Band_.id));
            }
            if (criteria.getInstrumentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getInstrumentId(), Artist_.instruments, Instrument_.id));
            }
            if (criteria.getRatingId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRatingId(), Artist_.ratings, RatingArtist_.id));
            }
        }
        return specification;
    }
}
