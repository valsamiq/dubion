package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Band;
import com.dubion.domain.RatingBand;

import com.dubion.repository.RatingBandRepository;
import com.dubion.repository.UserRepository;
import com.dubion.security.SecurityUtils;
import com.dubion.service.BandService;
import com.dubion.service.RatingBandQueryService;
import com.dubion.service.RatingBandService;
import com.dubion.service.dto.StatsBandRating;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RatingBand.
 */
@RestController
@RequestMapping("/api")
public class RatingBandResource {

    private final Logger log = LoggerFactory.getLogger(RatingBandResource.class);

    private static final String ENTITY_NAME = "ratingBand";

    private final RatingBandRepository ratingBandRepository;

    private final RatingBandService ratingBandService;

    private final BandService bandService;

    private final RatingBandQueryService ratingBandQueryService;

    private final UserRepository userRepository;

    public RatingBandResource(RatingBandRepository ratingBandRepository, RatingBandService ratingBandService, BandService bandService, RatingBandQueryService ratingBandQueryService, UserRepository userRepository) {
        this.ratingBandRepository = ratingBandRepository;
        this.ratingBandService = ratingBandService;
        this.bandService = bandService;
        this.ratingBandQueryService = ratingBandQueryService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /rating-bands : Create a new ratingBand.
     *
     * @param ratingBand the ratingBand to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ratingBand, or with status 400 (Bad Request) if the ratingBand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rating-bands")
    @Timed
    public ResponseEntity<RatingBand> createRatingBand(@RequestBody RatingBand ratingBand) throws URISyntaxException {
        log.debug("REST request to save RatingBand : {}", ratingBand);
        if (ratingBand.getId() != null) {
            throw new BadRequestAlertException("A new ratingBand cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<RatingBand> ratingBandOptional = ratingBandRepository.findByBandAndUserLogin(ratingBand.getBand(), SecurityUtils.getCurrentUserLogin());

        RatingBand result;

        if (ratingBandOptional.isPresent()) {
            RatingBand ratingBandActual = ratingBandOptional.get();
            ratingBandActual.setRating(ratingBand.getRating());
            ratingBandActual.setDate(ZonedDateTime.now());
            result = ratingBandService.save(ratingBandActual);
        }else{
            ratingBand.setDate(ZonedDateTime.now());
            ratingBand.setUser(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get());
            result = ratingBandService.save(ratingBand);
        }

        return ResponseEntity.created(new URI("/api/rating-bands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rating-bands : Updates an existing ratingBand.
     *
     * @param ratingBand the ratingBand to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ratingBand,
     * or with status 400 (Bad Request) if the ratingBand is not valid,
     * or with status 500 (Internal Server Error) if the ratingBand couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rating-bands")
    @Timed
    public ResponseEntity<RatingBand> updateRatingBand(@RequestBody RatingBand ratingBand) throws URISyntaxException {
        log.debug("REST request to update RatingBand : {}", ratingBand);
        if (ratingBand.getId() == null) {
            return createRatingBand(ratingBand);
        }
        RatingBand result = ratingBandRepository.save(ratingBand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ratingBand.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rating-bands : get all the ratingBands.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ratingBands in body
     */
    @GetMapping("/rating-bands")
    @Timed
    public List<RatingBand> getAllRatingBands() {
        log.debug("REST request to get all RatingBands");
        return ratingBandRepository.findAll();
        }

    /**
     * GET  /rating-bands/:id : get the "id" ratingBand.
     *
     * @param id the id of the ratingBand to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingBand, or with status 404 (Not Found)
     */
    @GetMapping("/rating-bands/{id}")
    @Timed
    public ResponseEntity<RatingBand> getRatingBand(@PathVariable Long id) {
        log.debug("REST request to get RatingBand : {}", id);
        RatingBand ratingBand = ratingBandRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ratingBand));
    }


    @GetMapping("/rating-bands/band/{id}")
    @Timed
    public ResponseEntity<RatingBand> getRatingByBand(@PathVariable Long id) {
        log.debug("REST request to get RatingBand : {}", id);
        Band band = bandService.findOne(id);

        return ResponseUtil.wrapOrNotFound(
            Optional.ofNullable(
                ratingBandRepository.findByBandAndUserLogin(band,SecurityUtils.getCurrentUserLogin()).get()));
    }


    /**
     * DELETE  /rating-bands/:id : delete the "id" ratingBand.
     *
     * @param id the id of the ratingBand to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rating-bands/{id}")
    @Timed
    public ResponseEntity<Void> deleteRatingBand(@PathVariable Long id) {
        log.debug("REST request to delete RatingBand : {}", id);
        ratingBandRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/rating-band-stats/{id}")
    @Timed
    public StatsBandRating getStats(@PathVariable Long id){
        return ratingBandRepository.findBandStats(id);
    }
}
