package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Beta;
import com.dubion.service.BetaService;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.BetaCriteria;
import com.dubion.service.BetaQueryService;
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
 * REST controller for managing Beta.
 */
@RestController
@RequestMapping("/api")
public class BetaResource {

    private final Logger log = LoggerFactory.getLogger(BetaResource.class);

    private static final String ENTITY_NAME = "beta";

    private final BetaService betaService;

    private final BetaQueryService betaQueryService;

    public BetaResource(BetaService betaService, BetaQueryService betaQueryService) {
        this.betaService = betaService;
        this.betaQueryService = betaQueryService;
    }

    /**
     * POST  /betas : Create a new beta.
     *
     * @param beta the beta to create
     * @return the ResponseEntity with status 201 (Created) and with body the new beta, or with status 400 (Bad Request) if the beta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/betas")
    @Timed
    public ResponseEntity<Beta> createBeta(@RequestBody Beta beta) throws URISyntaxException {
        log.debug("REST request to save Beta : {}", beta);
        if (beta.getId() != null) {
            throw new BadRequestAlertException("A new beta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Beta result = betaService.save(beta);
        return ResponseEntity.created(new URI("/api/betas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /betas : Updates an existing beta.
     *
     * @param beta the beta to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated beta,
     * or with status 400 (Bad Request) if the beta is not valid,
     * or with status 500 (Internal Server Error) if the beta couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/betas")
    @Timed
    public ResponseEntity<Beta> updateBeta(@RequestBody Beta beta) throws URISyntaxException {
        log.debug("REST request to update Beta : {}", beta);
        if (beta.getId() == null) {
            return createBeta(beta);
        }
        Beta result = betaService.save(beta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, beta.getId().toString()))
            .body(result);
    }

    /**
     * GET  /betas : get all the betas.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of betas in body
     */
    @GetMapping("/betas")
    @Timed
    public ResponseEntity<List<Beta>> getAllBetas(BetaCriteria criteria) {
        log.debug("REST request to get Betas by criteria: {}", criteria);
        List<Beta> entityList = betaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /betas/:id : get the "id" beta.
     *
     * @param id the id of the beta to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the beta, or with status 404 (Not Found)
     */
    @GetMapping("/betas/{id}")
    @Timed
    public ResponseEntity<Beta> getBeta(@PathVariable Long id) {
        log.debug("REST request to get Beta : {}", id);
        Beta beta = betaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(beta));
    }

    /**
     * DELETE  /betas/:id : delete the "id" beta.
     *
     * @param id the id of the beta to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/betas/{id}")
    @Timed
    public ResponseEntity<Void> deleteBeta(@PathVariable Long id) {
        log.debug("REST request to delete Beta : {}", id);
        betaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
