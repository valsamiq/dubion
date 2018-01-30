package com.dubion.service;

import com.dubion.domain.Social;
import com.dubion.domain.Social_;
import com.dubion.repository.SocialRepository;
import com.dubion.service.dto.SocialCriteria;
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
public class SocialQueryService extends QueryService<Social>{

    private final Logger log = LoggerFactory.getLogger(SocialQueryService.class);


    private final SocialRepository socialRepository;

    public SocialQueryService(SocialRepository socialRepository){
        this.socialRepository = socialRepository;
    }

    @Transactional(readOnly = true)
    public List<Social> findByCriteria(SocialCriteria criteria){
        log.debug("find by criteria : {}", criteria);
        final Specifications<Social> specification = createSpecification(criteria);
        return socialRepository.findAll(specification);
    }
    @Transactional(readOnly = true)
    public Page<Social> findByCriteria(SocialCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Social> specification = createSpecification(criteria);
        return socialRepository.findAll(specification, page);
    }
    private Specifications<Social> createSpecification(SocialCriteria criteria) {
        Specifications<Social> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Social_.id));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Social_.url));
            }
            if (criteria.getOfficial() != null) {
                specification = specification.and(buildSpecification(criteria.getOfficial(), Social_.official));
            }
            if (criteria.getFacebook() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebook(), Social_.facebook));
            }
            if (criteria.getTwitter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTwitter(), Social_.twitter));
            }
            if (criteria.getYouTube() != null) {
                specification = specification.and(buildSpecification(criteria.getYouTube(), Social_.youTube));
            }
            if (criteria.getGooglePlus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGooglePlus(), Social_.googlePlus));
            }
            if (criteria.getInstagram() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstagram(), Social_.instagram));
            }
        }
        return specification;
    }
}
