package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Album;
import com.dubion.domain.RatingAlbum;
import com.dubion.repository.UserRepository;
import com.dubion.security.SecurityUtils;
import com.dubion.service.AlbumService;
import com.dubion.service.RatingAlbumService;
import com.dubion.repository.RatingAlbumRepository;
import com.dubion.service.dto.StatsAlbumRating;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.RatingAlbumCriteria;
import com.dubion.service.RatingAlbumQueryService;
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
 * REST controller for managing RatingAlbum.
 */
@RestController
@RequestMapping("/api")
public class RatingAlbumResource {

    private final Logger log = LoggerFactory.getLogger(RatingAlbumResource.class);

    private static final String ENTITY_NAME = "ratingAlbum";

    private final RatingAlbumRepository ratingAlbumRepository;

    private final RatingAlbumService ratingAlbumService;

    private final AlbumService albumService;

    private final RatingAlbumQueryService ratingAlbumQueryService;

    private final UserRepository userRepository;

    public RatingAlbumResource(RatingAlbumRepository ratingAlbumRepository, RatingAlbumService ratingAlbumService, AlbumService albumService, RatingAlbumQueryService ratingAlbumQueryService, UserRepository userRepository) {
        this.ratingAlbumRepository = ratingAlbumRepository;
        this.ratingAlbumService = ratingAlbumService;
        this.albumService = albumService;
        this.ratingAlbumQueryService = ratingAlbumQueryService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /rating-albums : Create a new ratingAlbum.
     *
     * @param ratingAlbum the ratingAlbum to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ratingAlbum, or with status 400 (Bad Request) if the ratingAlbum has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rating-albums")
    @Timed
    public ResponseEntity<RatingAlbum> createRatingAlbum(@RequestBody RatingAlbum ratingAlbum) throws URISyntaxException {
        log.debug("REST request to save RatingAlbum : {}", ratingAlbum);
        if (ratingAlbum.getId() != null) {
            throw new BadRequestAlertException("A new ratingAlbum cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<RatingAlbum> ratingAlbumOptional = ratingAlbumRepository.findByAlbumAndUserLogin(ratingAlbum.getAlbum(), SecurityUtils.getCurrentUserLogin());

        RatingAlbum result;

        if (ratingAlbumOptional.isPresent()) {
            RatingAlbum ratingAlbumActual = ratingAlbumOptional.get();
            ratingAlbumActual.setRating(ratingAlbum.getRating());
            ratingAlbumActual.setDate(ZonedDateTime.now());
            result = ratingAlbumService.save(ratingAlbumActual);
        }else{
            ratingAlbum.setDate(ZonedDateTime.now());
            ratingAlbum.setUser(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get());
            result = ratingAlbumService.save(ratingAlbum);
        }


        return ResponseEntity.created(new URI("/api/rating-album/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rating-albums : Updates an existing ratingAlbum.
     *
     * @param ratingAlbum the ratingAlbum to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ratingAlbum,
     * or with status 400 (Bad Request) if the ratingAlbum is not valid,
     * or with status 500 (Internal Server Error) if the ratingAlbum couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rating-albums")
    @Timed
    public ResponseEntity<RatingAlbum> updateRatingAlbum(@RequestBody RatingAlbum ratingAlbum) throws URISyntaxException {
        log.debug("REST request to update RatingAlbum : {}", ratingAlbum);
        if (ratingAlbum.getId() == null) {
            return createRatingAlbum(ratingAlbum);
        }
        RatingAlbum result = ratingAlbumService.save(ratingAlbum);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ratingAlbum.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rating-albums : get all the ratingAlbums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ratingAlbums in body
     */
    @GetMapping("/rating-albums")
    @Timed
    public ResponseEntity<List<RatingAlbum>> getAllRatingAlbums(RatingAlbumCriteria criteria) {
        log.debug("REST request to get RatingAlbums by criteria: {}", criteria);
        List<RatingAlbum> entityList = ratingAlbumQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
        }

    /**
     * GET  /rating-albums/:id : get the "id" ratingAlbum.
     *
     * @param id the id of the ratingAlbum to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingAlbum, or with status 404 (Not Found)
     */
    @GetMapping("/rating-albums/{id}")
    @Timed
    public ResponseEntity<RatingAlbum> getRatingAlbum(@PathVariable Long id) {
        log.debug("REST request to get RatingAlbum : {}", id);
        RatingAlbum ratingAlbum = ratingAlbumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ratingAlbum));
    }

    /**
     * GET  /rating-albums/:id : get the "id" ratingAlbum.
     *
     * @param id the id of the ratingAlbum to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ratingAlbum, or with status 404 (Not Found)
     */
    @GetMapping("/rating-albums/album/{id}")
    @Timed
    public ResponseEntity<RatingAlbum> getRatingByAlbum(@PathVariable Long id) {
        log.debug("REST request to get RatingAlbum : {}", id);
        Album album = albumService.findOne(id);

        return ResponseUtil.wrapOrNotFound(
            Optional.ofNullable(
                ratingAlbumRepository.findByAlbumAndUserLogin(album,SecurityUtils.getCurrentUserLogin()).get()));
    }

    /**
     * DELETE  /rating-albums/:id : delete the "id" ratingAlbum.
     *
     * @param id the id of the ratingAlbum to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rating-albums/{id}")
    @Timed
    public ResponseEntity<Void> deleteRatingAlbum(@PathVariable Long id) {
        log.debug("REST request to delete RatingAlbum : {}", id);
        ratingAlbumService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/rating-album-stats/{id}")
    @Timed
    public StatsAlbumRating getStats(@PathVariable Long id){
        return ratingAlbumRepository.findAlbumStats(id);
    }
}
