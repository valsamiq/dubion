package com.dubion.service;

import com.dubion.domain.Album;
import com.dubion.domain.Album_;
import com.dubion.domain.Band_;
import com.dubion.domain.Genre_;
import com.dubion.repository.AlbumRepository;
import com.dubion.service.dto.AlbumCriteria;
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
 * Service for executing complex queries for Album entities in the database.
 * The main input is a {@link AlbumCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Album} or a {@link Page} of {%link Album} which fulfills the criterias
 */

@Service
@Transactional(readOnly = true)
public class AlbumQueryService extends QueryService<Album>{
    private final Logger log = LoggerFactory.getLogger(AlbumQueryService.class);

    private final AlbumRepository albumRepository;

    public AlbumQueryService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    /**
     * Return a {@link List} of {%link Album} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public List<Album> findByCriteria(AlbumCriteria criteria){
        log.debug("find by criteria : {}", criteria);
        final Specifications<Album> specification = createSpecification(criteria);
        return albumRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Band} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public Page<Album> findByCriteria(AlbumCriteria criteria, Pageable page){
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Album> specification = createSpecification(criteria);
        return albumRepository.findAll(specification, page);
    }

    /**
     * Function to convert AlbumCriteria to a {@link Specifications}
     */
    private Specifications<Album> createSpecification(AlbumCriteria criteria){
        Specifications<Album> specification = Specifications.where(null);
        if (criteria != null){
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Album_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Album_.name));
            }
            if (criteria.getReleaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReleaseDate(), Album_.releaseDate));
            }
            if (criteria.getGenreId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGenreId(), Album_.genres, Genre_.id));
            }

            if (criteria.getGenreName() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getGenreName(), Album_.genres, Genre_.name));
            }
        }
        return specification;
    }
}
