package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Alpha;
import com.dubion.service.AlphaService;
import com.dubion.service.TicketMasterAPI.TicketMasterDTOService;
import com.dubion.service.dto.TicketMasterAPI.TicketMasterAPI;
import com.dubion.service.dto.ip_API.IdApiDTO;
import com.dubion.service.ip_API.Ip_apiDTOService;
import com.dubion.service.TicketMasterAPI.TicketMasterDTOService;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.AlphaCriteria;
import com.dubion.service.AlphaQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Alpha.
 */
@RestController
@RequestMapping("/api")
public class AlphaResource {

    private final Logger log = LoggerFactory.getLogger(AlphaResource.class);

    private static final String ENTITY_NAME = "alpha";

    private final AlphaService alphaService;

    private final AlphaQueryService alphaQueryService;

    private final Ip_apiDTOService ip_apiDTOService;

    private final TicketMasterDTOService ticketMasterDTOService;

    public AlphaResource(AlphaService alphaService, AlphaQueryService alphaQueryService,Ip_apiDTOService ip_apiDTOService, TicketMasterDTOService ticketMasterDTOService) {
        this.alphaService = alphaService;
        this.alphaQueryService = alphaQueryService;
        this.ip_apiDTOService = ip_apiDTOService;
        this.ticketMasterDTOService = ticketMasterDTOService;
    }

    /**
     * POST  /alphas : Create a new alpha.
     *
     * @param alpha the alpha to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alpha, or with status 400 (Bad Request) if the alpha has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/alphas")
    @Timed
    public ResponseEntity<Alpha> createAlpha(@RequestBody Alpha alpha) throws URISyntaxException {
        log.debug("REST request to save Alpha : {}", alpha);
        if (alpha.getId() != null) {
            throw new BadRequestAlertException("A new alpha cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Alpha result = alphaService.save(alpha);
        return ResponseEntity.created(new URI("/api/alphas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/a")
    @Timed
    public ResponseEntity<IdApiDTO> cooldenadas() throws IOException {
        IdApiDTO song = ip_apiDTOService.getCoordinatesUser();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }/**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/getByCity")
    @Timed
    public ResponseEntity<TicketMasterAPI> getCity() throws IOException {
        TicketMasterAPI song = ticketMasterDTOService.getCity();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }
    /**
     * PUT  /alphas : Updates an existing alpha.
     *
     * @param alpha the alpha to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alpha,
     * or with status 400 (Bad Request) if the alpha is not valid,
     * or with status 500 (Internal Server Error) if the alpha couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/alphas")
    @Timed
    public ResponseEntity<Alpha> updateAlpha(@RequestBody Alpha alpha) throws URISyntaxException {
        log.debug("REST request to update Alpha : {}", alpha);
        if (alpha.getId() == null) {
            return createAlpha(alpha);
        }
        Alpha result = alphaService.save(alpha);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alpha.getId().toString()))
            .body(result);
    }

    /**
     * GET  /alphas : get all the alphas.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of alphas in body
     */
    @GetMapping("/alphas")
    @Timed
    public ResponseEntity<List<Alpha>> getAllAlphas(AlphaCriteria criteria) {
        log.debug("REST request to get Alphas by criteria: {}", criteria);
        List<Alpha> entityList = alphaQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /alphas/:id : get the "id" alpha.
     *
     * @param id the id of the alpha to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alpha, or with status 404 (Not Found)
     */
    @GetMapping("/alphas/{id}")
    @Timed
    public ResponseEntity<Alpha> getAlpha(@PathVariable Long id) {
        log.debug("REST request to get Alpha : {}", id);
        Alpha alpha = alphaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alpha));
    }

    /**
     * DELETE  /alphas/:id : delete the "id" alpha.
     *
     * @param id the id of the alpha to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/alphas/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlpha(@PathVariable Long id) {
        log.debug("REST request to delete Alpha : {}", id);
        alphaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public TicketMasterDTOService getTicketMasterDTOService() {
        return ticketMasterDTOService;
    }
}
