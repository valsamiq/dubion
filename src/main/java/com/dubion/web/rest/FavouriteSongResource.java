package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Band;
import com.dubion.domain.FavouriteSong;
import com.dubion.service.BandQueryService;
import com.dubion.service.BandService;
import com.dubion.service.FavouriteSongService;
import com.dubion.repository.FavouriteSongRepository;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.FavouriteSongCriteria;
import com.dubion.service.FavouriteSongQueryService;
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
 * REST controller for managing FavouriteSong.
 */
@RestController
@RequestMapping("/api")
public class FavouriteSongResource {

    private final Logger log = LoggerFactory.getLogger(FavouriteSongResource.class);

    private static final String ENTITY_NAME = "favouriteSong";

    private final FavouriteSongRepository favouriteSongRepository;

    private final FavouriteSongService favouriteSongService;

    private final FavouriteSongQueryService favouriteSongQueryService;

    public FavouriteSongResource(FavouriteSongRepository favouriteSongRepository,FavouriteSongService favouriteSongService, FavouriteSongQueryService favouriteSongQueryService) {
        this.favouriteSongRepository = favouriteSongRepository;
        this.favouriteSongService = favouriteSongService;
        this.favouriteSongQueryService = favouriteSongQueryService;
    }

    /**
     * POST  /favourite-songs : Create a new favouriteSong.
     *
     * @param favouriteSong the favouriteSong to create
     * @return the ResponseEntity with status 201 (Created) and with body the new favouriteSong, or with status 400 (Bad Request) if the favouriteSong has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/favourite-songs")
    @Timed
    public ResponseEntity<FavouriteSong> createFavouriteSong(@RequestBody FavouriteSong favouriteSong) throws URISyntaxException {
        log.debug("REST request to save FavouriteSong : {}", favouriteSong);
        if (favouriteSong.getId() != null) {
            throw new BadRequestAlertException("A new favouriteSong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FavouriteSong result = favouriteSongService.save(favouriteSong);
        return ResponseEntity.created(new URI("/api/favourite-song/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /favourite-songs : Updates an existing favouriteSong.
     *
     * @param favouriteSong the favouriteSong to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated favouriteSong,
     * or with status 400 (Bad Request) if the favouriteSong is not valid,
     * or with status 500 (Internal Server Error) if the favouriteSong couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/favourite-songs")
    @Timed
    public ResponseEntity<FavouriteSong> updateFavouriteSong(@RequestBody FavouriteSong favouriteSong) throws URISyntaxException {
        log.debug("REST request to update FavouriteSong : {}", favouriteSong);
        if (favouriteSong.getId() == null) {
            return createFavouriteSong(favouriteSong);
        }
        FavouriteSong result = favouriteSongService.save(favouriteSong);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, favouriteSong.getId().toString()))
            .body(result);
    }

    /**
     * GET  /favourite-songs : get all the favouriteSongs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of favouriteSongs in body
     */
    @GetMapping("/favourite-songs")
    @Timed
    public ResponseEntity <List<FavouriteSong>> getAllFavouriteSongs(FavouriteSongCriteria criteria) {
        log.debug("REST request to get FavouriteSongs by criteria: {}", criteria);
        List<FavouriteSong> entityList = favouriteSongQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
        }

    /**
     * GET  /favourite-songs/:id : get the "id" favouriteSong.
     *
     * @param id the id of the favouriteSong to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the favouriteSong, or with status 404 (Not Found)
     */
    @GetMapping("/favourite-songs/{id}")
    @Timed
    public ResponseEntity<FavouriteSong> getFavouriteSong(@PathVariable Long id) {
        log.debug("REST request to get FavouriteSong : {}", id);
        FavouriteSong favouriteSong = favouriteSongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(favouriteSong));
    }

    /**
     * DELETE  /favourite-songs/:id : delete the "id" favouriteSong.
     *
     * @param id the id of the favouriteSong to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/favourite-songs/{id}")
    @Timed
    public ResponseEntity<Void> deleteFavouriteSong(@PathVariable Long id) {
        log.debug("REST request to delete FavouriteSong : {}", id);
        favouriteSongService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
