package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Label;

import com.dubion.repository.LabelRepository;
import com.dubion.service.LabelQueryService;
import com.dubion.service.LabelService;
import com.dubion.service.dto.LabelCriteria;
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
 * REST controller for managing Label.
 */
@RestController
@RequestMapping("/api")
public class LabelResource {

    private final Logger log = LoggerFactory.getLogger(LabelResource.class);

    private static final String ENTITY_NAME = "label";

    private final LabelRepository labelRepository;

    private final LabelService labelService;

    private final LabelQueryService labelQueryService;

    public LabelResource(LabelRepository labelRepository, LabelService labelService, LabelQueryService labelQueryService) {
        this.labelRepository = labelRepository;
        this.labelService = labelService;
        this.labelQueryService = labelQueryService;
    }


    /**
     * POST  /labels : Create a new label.
     *
     * @param label the label to create
     * @return the ResponseEntity with status 201 (Created) and with body the new label, or with status 400 (Bad Request) if the label has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/labels")
    @Timed
    public ResponseEntity<Label> createLabel(@RequestBody Label label) throws URISyntaxException {
        log.debug("REST request to save Label : {}", label);
        if (label.getId() != null) {
            throw new BadRequestAlertException("A new label cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Label result = labelService.save(label);
        return ResponseEntity.created(new URI("/api/labels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /labels : Updates an existing label.
     *
     * @param label the label to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated label,
     * or with status 400 (Bad Request) if the label is not valid,
     * or with status 500 (Internal Server Error) if the label couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/labels")
    @Timed
    public ResponseEntity<Label> updateLabel(@RequestBody Label label) throws URISyntaxException {
        log.debug("REST request to update Label : {}", label);
        if (label.getId() == null) {
            return createLabel(label);
        }
        Label result = labelService.save(label);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, label.getId().toString()))
            .body(result);
    }

    /**
     * GET  /labels : get all the labels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of labels in body
     */
    @GetMapping("/labels")
    @Timed
    public ResponseEntity<List<Label>> getAllLabels(LabelCriteria criteria) {
        log.debug("REST request to get all Labels by Criteria {}", criteria);
        List<Label> entityList = labelQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }
    /**
     * GET  /labels/:id : get the "id" label.
     *
     * @param id the id of the label to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the label, or with status 404 (Not Found)
     */
    @GetMapping("/labels/{id}")
    @Timed
    public ResponseEntity<Label> getLabel(@PathVariable Long id) {
        log.debug("REST request to get Label : {}", id);
        Label label = labelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(label));
    }

    /**
     * DELETE  /labels/:id : delete the "id" label.
     *
     * @param id the id of the label to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/labels/{id}")
    @Timed
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        log.debug("REST request to delete Label : {}", id);
        labelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/get-label-name/{labelName}")
    public Label getLabelByName(@PathVariable String labelName){
        return labelRepository.findByNameContaining(labelName);
        //Intelliji seggests to make this static
    }
}
