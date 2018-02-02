package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Gamma;
import com.dubion.service.GammaService;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.GammaCriteria;
import com.dubion.service.GammaQueryService;
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
 * REST controller for managing Gamma.
 */
@RestController
@RequestMapping("/api")
public class GammaResource {

    private final Logger log = LoggerFactory.getLogger(GammaResource.class);

    private static final String ENTITY_NAME = "gamma";

    private final GammaService gammaService;

    private final GammaQueryService gammaQueryService;

    public GammaResource(GammaService gammaService, GammaQueryService gammaQueryService) {
        this.gammaService = gammaService;
        this.gammaQueryService = gammaQueryService;
    }

    /**
     * POST  /gammas : Create a new gamma.
     *
     * @param gamma the gamma to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gamma, or with status 400 (Bad Request) if the gamma has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gammas")
    @Timed
    public ResponseEntity<Gamma> createGamma(@RequestBody Gamma gamma) throws URISyntaxException {
        log.debug("REST request to save Gamma : {}", gamma);
        if (gamma.getId() != null) {
            throw new BadRequestAlertException("A new gamma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gamma result = gammaService.save(gamma);
        return ResponseEntity.created(new URI("/api/gammas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gammas : Updates an existing gamma.
     *
     * @param gamma the gamma to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gamma,
     * or with status 400 (Bad Request) if the gamma is not valid,
     * or with status 500 (Internal Server Error) if the gamma couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gammas")
    @Timed
    public ResponseEntity<Gamma> updateGamma(@RequestBody Gamma gamma) throws URISyntaxException {
        log.debug("REST request to update Gamma : {}", gamma);
        if (gamma.getId() == null) {
            return createGamma(gamma);
        }
        Gamma result = gammaService.save(gamma);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gamma.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gammas : get all the gammas.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of gammas in body
     */
    @GetMapping("/gammas")
    @Timed
    public ResponseEntity<List<Gamma>> getAllGammas(GammaCriteria criteria) {
        log.debug("REST request to get Gammas by criteria: {}", criteria);
        List<Gamma> entityList = gammaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /gammas/:id : get the "id" gamma.
     *
     * @param id the id of the gamma to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gamma, or with status 404 (Not Found)
     */
    @GetMapping("/gammas/{id}")
    @Timed
    public ResponseEntity<Gamma> getGamma(@PathVariable Long id) {
        log.debug("REST request to get Gamma : {}", id);
        Gamma gamma = gammaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(gamma));
    }

    /**
     * DELETE  /gammas/:id : delete the "id" gamma.
     *
     * @param id the id of the gamma to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gammas/{id}")
    @Timed
    public ResponseEntity<Void> deleteGamma(@PathVariable Long id) {
        log.debug("REST request to delete Gamma : {}", id);
        gammaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
