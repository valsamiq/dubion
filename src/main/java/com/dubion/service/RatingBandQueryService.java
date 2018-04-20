package com.dubion.service;

import com.dubion.domain.RatingBand;
import com.dubion.domain.RatingBand_;
import com.dubion.repository.RatingBandRepository;
import com.dubion.service.dto.RatingBandCriteria;
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
public class RatingBandQueryService extends QueryService <RatingBand>{

    private final Logger log = LoggerFactory.getLogger(RatingBandQueryService.class);


    private final RatingBandRepository ratingBandRepository;

    public RatingBandQueryService(RatingBandRepository ratingBandRepository) {
        this.ratingBandRepository = ratingBandRepository;
    }
    @Transactional(readOnly = true)
    public List<RatingBand> findByCriteria(RatingBandCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<RatingBand> specification = createSpecification(criteria);
        return ratingBandRepository.findAll(specification);
    }
    @Transactional(readOnly = true)
    public Page<RatingBand> findByCriteria(RatingBandCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<RatingBand> specification = createSpecification(criteria);
        return ratingBandRepository.findAll(specification, page);
    }

    private Specifications<RatingBand> createSpecification(RatingBandCriteria criteria) {
        Specifications<RatingBand> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RatingBand_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), RatingBand_.date));
            }
            if (criteria.getRating() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRating(), RatingBand_.rating));
            }
        }
        return specification;
    }
}
