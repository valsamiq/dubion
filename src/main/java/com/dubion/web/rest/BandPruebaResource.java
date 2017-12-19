package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.BandPrueba;
import com.dubion.service.BandPruebaService;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.BandPruebaCriteria;
import com.dubion.service.BandPruebaQueryService;
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
 * REST controller for managing BandPrueba.
 */
@RestController
@RequestMapping("/api")
public class BandPruebaResource {

    private final Logger log = LoggerFactory.getLogger(BandPruebaResource.class);

    private static final String ENTITY_NAME = "bandPrueba";

    private final BandPruebaService bandPruebaService;

    private final BandPruebaQueryService bandPruebaQueryService;

    public BandPruebaResource(BandPruebaService bandPruebaService, BandPruebaQueryService bandPruebaQueryService) {
        this.bandPruebaService = bandPruebaService;
        this.bandPruebaQueryService = bandPruebaQueryService;
    }

    /**
     * POST  /band-pruebas : Create a new bandPrueba.
     *
     * @param bandPrueba the bandPrueba to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bandPrueba, or with status 400 (Bad Request) if the bandPrueba has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/band-pruebas")
    @Timed
    public ResponseEntity<BandPrueba> createBandPrueba(@RequestBody BandPrueba bandPrueba) throws URISyntaxException {
        log.debug("REST request to save BandPrueba : {}", bandPrueba);
        if (bandPrueba.getId() != null) {
            throw new BadRequestAlertException("A new bandPrueba cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BandPrueba result = bandPruebaService.save(bandPrueba);
        return ResponseEntity.created(new URI("/api/band-pruebas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /band-pruebas : Updates an existing bandPrueba.
     *
     * @param bandPrueba the bandPrueba to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bandPrueba,
     * or with status 400 (Bad Request) if the bandPrueba is not valid,
     * or with status 500 (Internal Server Error) if the bandPrueba couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/band-pruebas")
    @Timed
    public ResponseEntity<BandPrueba> updateBandPrueba(@RequestBody BandPrueba bandPrueba) throws URISyntaxException {
        log.debug("REST request to update BandPrueba : {}", bandPrueba);
        if (bandPrueba.getId() == null) {
            return createBandPrueba(bandPrueba);
        }
        BandPrueba result = bandPruebaService.save(bandPrueba);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bandPrueba.getId().toString()))
            .body(result);
    }

    /**
     * GET  /band-pruebas : get all the bandPruebas.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of bandPruebas in body
     */
    @GetMapping("/band-pruebas")
    @Timed
    public ResponseEntity<List<BandPrueba>> getAllBandPruebas(BandPruebaCriteria criteria) {
        log.debug("REST request to get BandPruebas by criteria: {}", criteria);
        List<BandPrueba> entityList = bandPruebaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /band-pruebas/:id : get the "id" bandPrueba.
     *
     * @param id the id of the bandPrueba to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bandPrueba, or with status 404 (Not Found)
     */
    @GetMapping("/band-pruebas/{id}")
    @Timed
    public ResponseEntity<BandPrueba> getBandPrueba(@PathVariable Long id) {
        log.debug("REST request to get BandPrueba : {}", id);
        BandPrueba bandPrueba = bandPruebaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bandPrueba));
    }

    /**
     * DELETE  /band-pruebas/:id : delete the "id" bandPrueba.
     *
     * @param id the id of the bandPrueba to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/band-pruebas/{id}")
    @Timed
    public ResponseEntity<Void> deleteBandPrueba(@PathVariable Long id) {
        log.debug("REST request to delete BandPrueba : {}", id);
        bandPruebaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
