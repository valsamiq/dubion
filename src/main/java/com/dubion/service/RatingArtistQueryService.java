package com.dubion.service;

import com.dubion.domain.RatingArtist;
import com.dubion.domain.RatingArtist_;
import com.dubion.repository.RatingArtistRepository;
import com.dubion.service.dto.RatingArtistCriteria;
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
public class RatingArtistQueryService extends QueryService <RatingArtist>{

    private final Logger log = LoggerFactory.getLogger(RatingArtistQueryService.class);


    private final RatingArtistRepository ratingArtistRepository;

    public RatingArtistQueryService(RatingArtistRepository ratingArtistRepository) {
        this.ratingArtistRepository = ratingArtistRepository;
    }
    @Transactional(readOnly = true)
    public List<RatingArtist> findByCriteria(RatingArtistCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<RatingArtist> specification = createSpecification(criteria);
        return ratingArtistRepository.findAll(specification);
    }
    @Transactional(readOnly = true)
    public Page<RatingArtist> findByCriteria(RatingArtistCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<RatingArtist> specification = createSpecification(criteria);
        return ratingArtistRepository.findAll(specification, page);
    }

    private Specifications<RatingArtist> createSpecification(RatingArtistCriteria criteria) {
        Specifications<RatingArtist> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RatingArtist_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), RatingArtist_.date));
            }
            if (criteria.getRating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRating(), RatingArtist_.rating));
            }
        }
        return specification;
    }
}
