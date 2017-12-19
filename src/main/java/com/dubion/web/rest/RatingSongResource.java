package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.RatingSong;

import com.dubion.repository.RatingSongRepository;
import com.dubion.service.dto.StatsSongsRating;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
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
 * REST controller for managing RatingSong.
 */
@RestController
@RequestMapping("/api")
public class RatingSongResource {

    private final Logger log = LoggerFactory.getLogger(RatingSongResource.class);

    private static final String ENTITY_NAME = "ratingSong";

    private final RatingSongRepository ratingSongRepository;

    public RatingSongResource(RatingSongRepository ratingSongRepository) {
        this.ratingSongRepository = ratingSongRepository;
    }

    /**
     * POST  /rating-songs : Create a new ratingSong.
     *
     * @param ratingSong the ratingSong to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ratingSong, or with status 400 (Bad Request) if the ratingSong has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rating-songs")
    @Timed
    public ResponseEntity<RatingSong> createRatingSong(@RequestBody RatingSong ratingSong) throws URISyntaxException {
        log.debug("REST request to save RatingSong : {}", ratingSong);
        if (ratingSong.getId() != null) {
            throw new BadRequestAlertException("A new ratingSong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RatingSong result = ratingSongRepository.save(ratingSong);
        return ResponseEntity.created(new URI("/api/rating-songs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rating-songs : Updates an existing ratingSong.
     *
     * @param ratingSong the ratingSong to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ratingSong,
     * or with status 400 (Bad Request) if the ratingSong is not valid,
     * or with status 500 (Internal Server Error) if the ratingSong couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rating-songs")
    @Timed
    public ResponseEntity<RatingSong> updateRatingSong(@RequestBody RatingSong ratingSong) throws URISyntaxException {
        log.debug("REST request to update RatingSong : {}", ratingSong);
        if (ratingSong.getId() == null) {
            return createRatingSong(ratingSong);
        }
        RatingSong result = ratingSongRepository.save(ratingSong);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ratingSong.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rating-songs : get all the ratingSongs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ratingSongs in body
     */
    @GetMapping("/rating-songs")
    @Timed
    public List<RatingSong> getAllRatingSongs() {
        log.debug("REST request to get all RatingSongs");
        return ratingSongRepository.findAll();
        }

    /**
     * GET  /rating-songs/:id : get the "id" ratingSong.
     *
     * @param id the id of the ratingSong to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingSong, or with status 404 (Not Found)
     */
    @GetMapping("/rating-songs/{id}")
    @Timed
    public ResponseEntity<RatingSong> getRatingSong(@PathVariable Long id) {
        log.debug("REST request to get RatingSong : {}", id);
        RatingSong ratingSong = ratingSongRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ratingSong));
    }

    /**
     * DELETE  /rating-songs/:id : delete the "id" ratingSong.
     *
     * @param id the id of the ratingSong to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rating-songs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRatingSong(@PathVariable Long id) {
        log.debug("REST request to delete RatingSong : {}", id);
        ratingSongRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    @GetMapping ("/rating-song-stats-{id}")
    @Timed
    public StatsSongsRating getStats(@PathVariable Long id){
        return ratingSongRepository.findSongStats(id);
    }
}
