package com.dubion.service;

import com.dubion.domain.Label;
import com.dubion.domain.Label_;
import com.dubion.domain.Band_;
import com.dubion.domain.Genre_;
import com.dubion.repository.LabelRepository;
import com.dubion.service.dto.LabelCriteria;
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
 * Service for executing complex queries for Label entities in the database.
 * The main input is a {@link LabelCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link Label} or a {@link Page} of {%link Label} which fulfills the criterias
 */

@Service
@Transactional(readOnly = true)
public class LabelQueryService extends QueryService<Label>{
    private final Logger log = LoggerFactory.getLogger(LabelQueryService.class);

    private final LabelRepository labelRepository;

    public LabelQueryService(LabelRepository labelRepository){
        this.labelRepository = labelRepository;
    }

    /**
     * Return a {@link List} of {%link Label} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public List<Label> findByCriteria(LabelCriteria criteria){
        log.debug("find by criteria : {}", criteria);
        final Specifications<Label> specification = createSpecification(criteria);
        return labelRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link Band} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public Page<Label> findByCriteria(LabelCriteria criteria, Pageable page){
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Label> specification = createSpecification(criteria);
        return labelRepository.findAll(specification, page);
    }

    /**
     * Function to convert LabelCriteria to a {@link Specifications}
     */
    private Specifications<Label> createSpecification(LabelCriteria criteria){
        Specifications<Label> specification = Specifications.where(null);
        if (criteria != null){
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Label_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Label_.name));
            }
//            if (criteria.getGenreId() != null) {
//                specification = specification.and(buildReferringEntitySpecification(criteria.getGenreId(), Label_.bands, Band_.id));
//            }
//
//            if (criteria.getGenreName() != null) {
//                specification = specification.and(buildReferringEntitySpecification(criteria.getGenreName(), Label_.bands, Band_.name));
//            }
        }
        return specification;
    }
}
