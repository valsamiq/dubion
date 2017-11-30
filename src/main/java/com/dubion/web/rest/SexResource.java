package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Sex;

import com.dubion.repository.SexRepository;
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
 * REST controller for managing Sex.
 */
@RestController
@RequestMapping("/api")
public class SexResource {

    private final Logger log = LoggerFactory.getLogger(SexResource.class);

    private static final String ENTITY_NAME = "sex";

    private final SexRepository sexRepository;

    public SexResource(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    /**
     * POST  /sexes : Create a new sex.
     *
     * @param sex the sex to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sex, or with status 400 (Bad Request) if the sex has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sexes")
    @Timed
    public ResponseEntity<Sex> createSex(@RequestBody Sex sex) throws URISyntaxException {
        log.debug("REST request to save Sex : {}", sex);
        if (sex.getId() != null) {
            throw new BadRequestAlertException("A new sex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sex result = sexRepository.save(sex);
        return ResponseEntity.created(new URI("/api/sexes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sexes : Updates an existing sex.
     *
     * @param sex the sex to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sex,
     * or with status 400 (Bad Request) if the sex is not valid,
     * or with status 500 (Internal Server Error) if the sex couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sexes")
    @Timed
    public ResponseEntity<Sex> updateSex(@RequestBody Sex sex) throws URISyntaxException {
        log.debug("REST request to update Sex : {}", sex);
        if (sex.getId() == null) {
            return createSex(sex);
        }
        Sex result = sexRepository.save(sex);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sex.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sexes : get all the sexes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sexes in body
     */
    @GetMapping("/sexes")
    @Timed
    public List<Sex> getAllSexes() {
        log.debug("REST request to get all Sexes");
        return sexRepository.findAll();
        }

    /**
     * GET  /sexes/:id : get the "id" sex.
     *
     * @param id the id of the sex to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sex, or with status 404 (Not Found)
     */
    @GetMapping("/sexes/{id}")
    @Timed
    public ResponseEntity<Sex> getSex(@PathVariable Long id) {
        log.debug("REST request to get Sex : {}", id);
        Sex sex = sexRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sex));
    }

    /**
     * DELETE  /sexes/:id : delete the "id" sex.
     *
     * @param id the id of the sex to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sexes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSex(@PathVariable Long id) {
        log.debug("REST request to delete Sex : {}", id);
        sexRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
