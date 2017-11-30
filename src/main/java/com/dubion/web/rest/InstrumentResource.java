package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Instrument;

import com.dubion.repository.InstrumentRepository;
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
 * REST controller for managing Instrument.
 */
@RestController
@RequestMapping("/api")
public class InstrumentResource {

    private final Logger log = LoggerFactory.getLogger(InstrumentResource.class);

    private static final String ENTITY_NAME = "instrument";

    private final InstrumentRepository instrumentRepository;

    public InstrumentResource(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    /**
     * POST  /instruments : Create a new instrument.
     *
     * @param instrument the instrument to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instrument, or with status 400 (Bad Request) if the instrument has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instruments")
    @Timed
    public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) throws URISyntaxException {
        log.debug("REST request to save Instrument : {}", instrument);
        if (instrument.getId() != null) {
            throw new BadRequestAlertException("A new instrument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Instrument result = instrumentRepository.save(instrument);
        return ResponseEntity.created(new URI("/api/instruments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instruments : Updates an existing instrument.
     *
     * @param instrument the instrument to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instrument,
     * or with status 400 (Bad Request) if the instrument is not valid,
     * or with status 500 (Internal Server Error) if the instrument couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instruments")
    @Timed
    public ResponseEntity<Instrument> updateInstrument(@RequestBody Instrument instrument) throws URISyntaxException {
        log.debug("REST request to update Instrument : {}", instrument);
        if (instrument.getId() == null) {
            return createInstrument(instrument);
        }
        Instrument result = instrumentRepository.save(instrument);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instrument.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instruments : get all the instruments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of instruments in body
     */
    @GetMapping("/instruments")
    @Timed
    public List<Instrument> getAllInstruments() {
        log.debug("REST request to get all Instruments");
        return instrumentRepository.findAll();
        }

    /**
     * GET  /instruments/:id : get the "id" instrument.
     *
     * @param id the id of the instrument to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instrument, or with status 404 (Not Found)
     */
    @GetMapping("/instruments/{id}")
    @Timed
    public ResponseEntity<Instrument> getInstrument(@PathVariable Long id) {
        log.debug("REST request to get Instrument : {}", id);
        Instrument instrument = instrumentRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(instrument));
    }

    /**
     * DELETE  /instruments/:id : delete the "id" instrument.
     *
     * @param id the id of the instrument to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instruments/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
        log.debug("REST request to delete Instrument : {}", id);
        instrumentRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
