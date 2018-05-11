package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Band;
import com.dubion.domain.FavouriteBand;
import com.dubion.repository.UserRepository;
import com.dubion.security.SecurityUtils;
import com.dubion.service.BandService;
import com.dubion.service.FavouriteBandService;
import com.dubion.repository.FavouriteBandRepository;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.FavouriteBandCriteria;
import com.dubion.service.FavouriteBandQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
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

    private final FavouriteBandService favouriteBandService;

    private final FavouriteBandQueryService favouriteBandQueryService;

    private final UserRepository userRepository;

    private final BandService bandService;

    public FavouriteBandResource(FavouriteBandRepository favouriteBandRepository, FavouriteBandService favouriteBandService, FavouriteBandQueryService favouriteBandQueryService, UserRepository userRepository, BandService bandService) {
        this.favouriteBandRepository = favouriteBandRepository;
        this.favouriteBandService = favouriteBandService;
        this.favouriteBandQueryService = favouriteBandQueryService;
        this.userRepository = userRepository;
        this.bandService = bandService;
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
        favouriteBand.setDate(LocalDate.now());
        favouriteBand.setUser(userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get());


        FavouriteBand result = favouriteBandService.save(favouriteBand);
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
        FavouriteBand result = favouriteBandService.save(favouriteBand);
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
    public ResponseEntity<List<FavouriteBand>> getAllFavouriteBands(FavouriteBandCriteria criteria) {
        log.debug("REST request to get FavouriteBands by criteri: {}", criteria);
        List<FavouriteBand> entityList = favouriteBandQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
        }

    @GetMapping("/favourite-bands-like")
    @Timed
    public ResponseEntity<List<Band>> findByFavoriteBand() {
        log.debug("REST request to get all FavouriteBands by criteria: {}");
        List<Band> entityList = favouriteBandRepository.findByFavoriteBand(SecurityUtils.getCurrentUserLogin());
        return ResponseEntity.ok().body(entityList);
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
        FavouriteBand favouriteBand = favouriteBandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(favouriteBand));
    }

    @GetMapping("/favourite-bands/band/{id}")
    @Timed
    public ResponseEntity<FavouriteBand> getFavouriteByBand(@PathVariable Long id) {
        log.debug("REST request to get FavouriteBand : {}", id);
        Band band = bandService.findOne(id);

        return ResponseUtil.wrapOrNotFound(
            Optional.ofNullable(
                favouriteBandRepository.findByBandAndUserLogin(band,SecurityUtils.getCurrentUserLogin()).get()));
    }
//    public ResponseEntity<FavouriteBand> getFavouriteByBand(@PathVariable Long id) {
//        log.debug("REST request to get FavouriteBand : {}", id);
//        Band band = bandService.findOne(id);
//
//        return new ResponseEntity<>(favouriteBandRepository.findByBandAndUserLogin(band, SecurityUtils.getCurrentUserLogin()), HttpStatus.OK);
//    }

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
        favouriteBandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
