package com.dubion.service;

import com.dubion.domain.Song;
import com.dubion.domain.Song_;
import com.dubion.repository.SongRepository;
import com.dubion.service.dto.SongCriteria;
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
@Transactional

public class SongService {

    private final Logger log = LoggerFactory.getLogger(SongService.class);

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song save(Song song ){
        log.debug("Request to save Song : {}", song);
        return songRepository.save(song);
    }

    @Transactional(readOnly = true)
    public List<Song> findAll() {
        log.debug("Request to get all Songs");
        return songRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Song findOne(Long id) {
        log.debug("Request to get Song : {}", id);
        return songRepository.findOne(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Song : {}", id);
        songRepository.delete(id);
    }

    @Service
    @Transactional(readOnly = true)
    public static class SongQueryService extends QueryService<Song> {

        private final Logger log = LoggerFactory.getLogger(SongQueryService.class);


        private final SongRepository songRepository;

        public SongQueryService(SongRepository songRepository) {
            this.songRepository = songRepository;
        }

        @Transactional(readOnly = true)
        public List<Song> findByCriteria(SongCriteria criteria) {
            log.debug("find by criteria : {}", criteria);
            final Specifications<Song> specification = createSpecification(criteria);
            return songRepository.findAll(specification);
        }

        @Transactional(readOnly = true)
        public Page<Song> findByCriteria(SongCriteria criteria, Pageable page) {
            log.debug("find by criteria : {}, page: {}", criteria, page);
            final Specifications<Song> specification = createSpecification(criteria);
            return songRepository.findAll(specification, page);
        }

        private Specifications<Song> createSpecification(SongCriteria criteria) {
            Specifications<Song> specification = Specifications.where(null);
            if (criteria != null) {
                if (criteria.getId() != null) {
                    specification = specification.and(buildSpecification(criteria.getId(), Song_.id));
                }
                if (criteria.getName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getName(), Song_.name));
                }
                if (criteria.getDuration() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getDuration(), Song_.duration));
                }
            }
            return specification;
        }
    }
}
