package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Band;
import com.dubion.domain.RatingArtist;
import com.dubion.service.BandQueryService;
import com.dubion.service.BandService;
import com.dubion.service.RatingArtistService;
import com.dubion.repository.RatingArtistRepository;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.RatingArtistCriteria;
import com.dubion.service.RatingArtistQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RatingArtist.
 */
@RestController
@RequestMapping("/api")
public class RatingArtistResource {

    private final Logger log = LoggerFactory.getLogger(RatingArtistResource.class);

    private static final String ENTITY_NAME = "ratingArtist";

    private final RatingArtistRepository ratingArtistRepository;

    private final RatingArtistService ratingArtistService;

    private final RatingArtistQueryService ratingArtistQueryService;

    public RatingArtistResource(RatingArtistRepository ratingArtistRepository,RatingArtistService ratingArtistService, RatingArtistQueryService ratingArtistQueryService) {
        this.ratingArtistRepository = ratingArtistRepository;
        this.ratingArtistService = ratingArtistService;
        this.ratingArtistQueryService = ratingArtistQueryService;
    }

    /**
     * POST  /rating-artists : Create a new ratingArtist.
     *
     * @param ratingArtist the ratingArtist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ratingArtist, or with status 400 (Bad Request) if the ratingArtist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rating-artists")
    @Timed
    public ResponseEntity<RatingArtist> createRatingArtist(@RequestBody RatingArtist ratingArtist) throws URISyntaxException {
        log.debug("REST request to save RatingArtist : {}", ratingArtist);
        if (ratingArtist.getId() != null) {
            throw new BadRequestAlertException("A new ratingArtist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RatingArtist result = ratingArtistService.save(ratingArtist);
        return ResponseEntity.created(new URI("/api/rating-artist/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rating-artists : Updates an existing ratingArtist.
     *
     * @param ratingArtist the ratingArtist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ratingArtist,
     * or with status 400 (Bad Request) if the ratingArtist is not valid,
     * or with status 500 (Internal Server Error) if the ratingArtist couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rating-artists")
    @Timed
    public ResponseEntity<RatingArtist> updateRatingArtist(@RequestBody RatingArtist ratingArtist) throws URISyntaxException {
        log.debug("REST request to update RatingArtist : {}", ratingArtist);
        if (ratingArtist.getId() == null) {
            return createRatingArtist(ratingArtist);
        }
        RatingArtist result = ratingArtistService.save(ratingArtist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ratingArtist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rating-artists : get all the ratingArtists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ratingArtists in body
     */
    @GetMapping("/rating-artists")
    @Timed
    public ResponseEntity<List<RatingArtist>> getAllRatingArtists(RatingArtistCriteria criteria) {
        log.debug("REST request to get RatingArtists by criteria: {}", criteria);
        List<RatingArtist> entityList = ratingArtistQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
        }

    /**
     * GET  /rating-artists/:id : get the "id" ratingArtist.
     *
     * @param id the id of the ratingArtist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingArtist, or with status 404 (Not Found)
     */
    @GetMapping("/rating-artists/{id}")
    @Timed
    public ResponseEntity<RatingArtist> getRatingArtist(@PathVariable Long id) {
        log.debug("REST request to get RatingArtist : {}", id);
        RatingArtist ratingArtist = ratingArtistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ratingArtist));
    }

    /**
     * DELETE  /rating-artists/:id : delete the "id" ratingArtist.
     *
     * @param id the id of the ratingArtist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rating-artists/{id}")
    @Timed
    public ResponseEntity<Void> deleteRatingArtist(@PathVariable Long id) {
        log.debug("REST request to delete RatingArtist : {}", id);
        ratingArtistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
