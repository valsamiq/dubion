package com.dubion.service;

import com.dubion.domain.Album_;
import com.dubion.domain.FavouriteAlbum;
import com.dubion.domain.FavouriteAlbum_;
import com.dubion.domain.User_;
import com.dubion.repository.FavouriteAlbumRepository;
import com.dubion.service.dto.FavouriteAlbumCriteria;
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
 * Service for executing complex queries for FavouriteAlbum entities in the database.
 * The main input is a {@link FavouriteAlbumCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link FavouriteAlbum} or a {@link Page} of {%link FavouriteAlbum} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class FavouriteAlbumQueryService extends QueryService<FavouriteAlbum> {

    private final Logger log = LoggerFactory.getLogger(FavouriteAlbumQueryService.class);


    private final FavouriteAlbumRepository favouriteAlbumRepository;

    public FavouriteAlbumQueryService(FavouriteAlbumRepository favouriteAlbumRepository) {
        this.favouriteAlbumRepository = favouriteAlbumRepository;
    }

    /**
     * Return a {@link List} of {%link FavouriteAlbum} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FavouriteAlbum> findByCriteria(FavouriteAlbumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FavouriteAlbum> specification = createSpecification(criteria);
        return favouriteAlbumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link FavouriteAlbum} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FavouriteAlbum> findByCriteria(FavouriteAlbumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FavouriteAlbum> specification = createSpecification(criteria);
        return favouriteAlbumRepository.findAll(specification, page);
    }

    /**
     * Function to convert FavouriteAlbumCriteria to a {@link Specifications}
     */
    private Specifications<FavouriteAlbum> createSpecification(FavouriteAlbumCriteria criteria) {
        Specifications<FavouriteAlbum> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FavouriteAlbum_.id));
            }
            if (criteria.getLiked() != null) {
                specification = specification.and(buildSpecification(criteria.getLiked(), FavouriteAlbum_.liked));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), FavouriteAlbum_.date));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserId(), FavouriteAlbum_.user, User_.id));
            }
            if (criteria.getAlbumId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAlbumId(), FavouriteAlbum_.album, Album_.id));
            }
        }
        return specification;
    }

}
