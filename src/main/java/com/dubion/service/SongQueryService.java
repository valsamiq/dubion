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

import com.dubion.domain.Song;
import com.dubion.domain.*; // for static metamodels
import com.dubion.repository.SongRepository;
import com.dubion.service.dto.SongCriteria;


/**
 * Service for executing complex queries for Song entities in the database.
 * The main input is a {@link SongCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Song} or a {@link Page} of {%link Song} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class SongQueryService extends QueryService<Song> {

    private final Logger log = LoggerFactory.getLogger(SongQueryService.class);


    private final SongRepository songRepository;

    public SongQueryService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    /**
     * Return a {@link List} of {%link Song} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Song> findByCriteria(SongCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Song> specification = createSpecification(criteria);
        return songRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Song} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Song> findByCriteria(SongCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Song> specification = createSpecification(criteria);
        return songRepository.findAll(specification, page);
    }

    /**
     * Function to convert SongCriteria to a {@link Specifications}
     */
    private Specifications<Song> createSpecification(SongCriteria criteria) {
        Specifications<Song> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Song_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Song_.name));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Song_.url));
            }
            if (criteria.getNapsterId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNapsterId(), Song_.napsterId));
            }
            if (criteria.getAlbumId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAlbumId(), Song_.albums, Album_.id));
            }
            if (criteria.getRatingId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRatingId(), Song_.ratings, RatingSong_.id));
            }
            if (criteria.getFavouriteId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFavouriteId(), Song_.favourites, FavouriteSong_.id));
            }
        }
        return specification;
    }

}
