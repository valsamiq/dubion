package com.dubion.service;

import com.dubion.domain.FavouriteSong;
import com.dubion.domain.FavouriteSong_;
import com.dubion.repository.FavouriteSongRepository;
import com.dubion.service.dto.FavouriteSongCriteria;
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
public class FavouriteSongQueryService extends QueryService<FavouriteSong> {

    private final Logger log = LoggerFactory.getLogger(FavouriteSongQueryService.class);


    private final FavouriteSongRepository favouriteSongRepository;

    public FavouriteSongQueryService(FavouriteSongRepository favouriteSongRepository) {
        this.favouriteSongRepository = favouriteSongRepository;
    }

    @Transactional(readOnly = true)
    public List<FavouriteSong> findByCriteria(FavouriteSongCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FavouriteSong> specification = createSpecification(criteria);
        return favouriteSongRepository.findAll(specification);
    }


    @Transactional(readOnly = true)
    public Page<FavouriteSong> findByCriteria(FavouriteSongCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FavouriteSong> specification = createSpecification(criteria);
        return favouriteSongRepository.findAll(specification, page);
    }

    /**
     * Function to convert BandCriteria to a {@link Specifications}
     */
    private Specifications<FavouriteSong> createSpecification(FavouriteSongCriteria criteria) {
        Specifications<FavouriteSong> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FavouriteSong_.id));
            }
            if (criteria.getLiked() != null) {
                specification = specification.and(buildSpecification(criteria.getLiked(), FavouriteSong_.liked));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), FavouriteSong_.date));
            }
        }
        return specification;
    }

}
