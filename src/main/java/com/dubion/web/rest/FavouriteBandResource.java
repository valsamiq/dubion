package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.FavouriteBand;

import com.dubion.repository.FavouriteBandRepository;
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
 * REST controller for managing FavouriteBand.
 */
@RestController
@RequestMapping("/api")
public class FavouriteBandResource {

    private final Logger log = LoggerFactory.getLogger(FavouriteBandResource.class);

    private static final String ENTITY_NAME = "favouriteBand";

    private final FavouriteBandRepository favouriteBandRepository;

    public FavouriteBandResource(FavouriteBandRepository favouriteBandRepository) {
        this.favouriteBandRepository = favouriteBandRepository;
    }

    /**
     * POST  /favourite-bands : Create a new favouriteBand.
     *
     * @param favouriteBand the favouriteBand to create
     * @return the ResponseEntity with status 201 (Created) and with body the new favouriteBand, or with status 400 (Bad Request) if the favouriteBand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/favourite-bands")
    @Timed
    public ResponseEntity<FavouriteBand> createFavouriteBand(@RequestBody FavouriteBand favouriteBand) throws URISyntaxException {
        log.debug("REST request to save FavouriteBand : {}", favouriteBand);
        if (favouriteBand.getId() != null) {
            throw new BadRequestAlertException("A new favouriteBand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FavouriteBand result = favouriteBandRepository.save(favouriteBand);
        return ResponseEntity.created(new URI("/api/favourite-bands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /favourite-bands : Updates an existing favouriteBand.
     *
     * @param favouriteBand the favouriteBand to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated favouriteBand,
     * or with status 400 (Bad Request) if the favouriteBand is not valid,
     * or with status 500 (Internal Server Error) if the favouriteBand couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/favourite-bands")
    @Timed
    public ResponseEntity<FavouriteBand> updateFavouriteBand(@RequestBody FavouriteBand favouriteBand) throws URISyntaxException {
        log.debug("REST request to update FavouriteBand : {}", favouriteBand);
        if (favouriteBand.getId() == null) {
            return createFavouriteBand(favouriteBand);
        }
        FavouriteBand result = favouriteBandRepository.save(favouriteBand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, favouriteBand.getId().toString()))
            .body(result);
    }

    /**
     * GET  /favourite-bands : get all the favouriteBands.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of favouriteBands in body
     */
    @GetMapping("/favourite-bands")
    @Timed
    public List<FavouriteBand> getAllFavouriteBands() {
        log.debug("REST request to get all FavouriteBands");
        return favouriteBandRepository.findAll();
        }

    /**
     * GET  /favourite-bands/:id : get the "id" favouriteBand.
     *
     * @param id the id of the favouriteBand to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the favouriteBand, or with status 404 (Not Found)
     */
    @GetMapping("/favourite-bands/{id}")
    @Timed
    public ResponseEntity<FavouriteBand> getFavouriteBand(@PathVariable Long id) {
        log.debug("REST request to get FavouriteBand : {}", id);
        FavouriteBand favouriteBand = favouriteBandRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(favouriteBand));
    }

    /**
     * DELETE  /favourite-bands/:id : delete the "id" favouriteBand.
     *
     * @param id the id of the favouriteBand to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/favourite-bands/{id}")
    @Timed
    public ResponseEntity<Void> deleteFavouriteBand(@PathVariable Long id) {
        log.debug("REST request to delete FavouriteBand : {}", id);
        favouriteBandRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
