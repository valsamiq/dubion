package com.dubion.service;

import com.dubion.domain.Artist;
import com.dubion.domain.Artist_;
import com.dubion.repository.ArtistRepository;
import com.dubion.service.dto.ArtistCriteria;
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
 * Service for executing complex queries for Band entities in the database.
 * The main input is a {@link ArtistCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Band} or a {@link Page} of {%link Band} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class ArtistQueryService extends QueryService<Artist>{

    private final Logger log = LoggerFactory.getLogger(ArtistQueryService.class);


    private final ArtistRepository artistRepository;

    public ArtistQueryService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    /**
     * Return a {@link List} of {%link Band} which matches the criteria from the database
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
     * Return a {@link Page} of {%link Band} which matches the criteria from the database
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
            if (criteria.getBirthdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthdate(), Artist_.birthDate));
            }
            if (criteria.getBio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBio(), Artist_.bio));
            }
        }
        return specification;
    }
}
