package com.dubion.service;

import com.dubion.domain.RatingAlbum;
import com.dubion.domain.RatingAlbum_;
import com.dubion.repository.RatingAlbumRepository;
import com.dubion.service.dto.RatingAlbumCriteria;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional(readOnly = true)
public class RatingAlbumQueryService extends QueryService <RatingAlbum>{

    private final Logger log = LoggerFactory.getLogger(RatingAlbumQueryService.class);


    private final RatingAlbumRepository ratingAlbumRepository;

    public RatingAlbumQueryService(RatingAlbumRepository ratingAlbumRepository) {
        this.ratingAlbumRepository = ratingAlbumRepository;
    }
    @Transactional(readOnly = true)
    public List<RatingAlbum> findByCriteria(RatingAlbumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<RatingAlbum> specification = createSpecification(criteria);
        return ratingAlbumRepository.findAll(specification);
    }
    @Transactional(readOnly = true)
    public Page<RatingAlbum> findByCriteria(RatingAlbumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<RatingAlbum> specification = createSpecification(criteria);
        return ratingAlbumRepository.findAll(specification, page);
    }

    private Specifications<RatingAlbum> createSpecification(RatingAlbumCriteria criteria) {
        Specifications<RatingAlbum> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RatingAlbum_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), RatingAlbum_.date));
            }
            if (criteria.getRating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRating(), RatingAlbum_.rating));
            }
        }
        return specification;
    }
}
