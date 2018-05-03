package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Album;
import com.dubion.domain.Song;
import com.dubion.repository.AlbumRepository;
import com.dubion.repository.SongRepository;
import com.dubion.service.NapsterAPI.NapsterDTOService;
import com.dubion.service.SongService;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.SongCriteria;
import com.dubion.service.SongQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Song.
 */
@RestController
@RequestMapping("/api")
public class SongResource {

    private final Logger log = LoggerFactory.getLogger(SongResource.class);

    private static final String ENTITY_NAME = "song";

    private final SongService songService;

    private final SongQueryService songQueryService;

    private final NapsterDTOService napsterDTOService;

    private final AlbumRepository albumRepository;

    private final SongRepository songRepository;

    public SongResource(SongService songService, SongQueryService songQueryService, NapsterDTOService napsterDTOService, AlbumRepository albumRepository, SongRepository songRepository) {
        this.songService = songService;
        this.songQueryService = songQueryService;
        this.napsterDTOService = napsterDTOService;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    /**
     * POST  /songs : Create a new song.
     *
     * @param song the song to create
     * @return the ResponseEntity with status 201 (Created) and with body the new song, or with status 400 (Bad Request) if the song has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/songs")
    @Timed
    public ResponseEntity<Song> createSong(@RequestBody Song song) throws URISyntaxException {
        log.debug("REST request to save Song : {}", song);
        if (song.getId() != null) {
            throw new BadRequestAlertException("A new song cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Song result = songService.save(song);
        return ResponseEntity.created(new URI("/api/songs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /songs : Updates an existing song.
     *
     * @param song the song to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated song,
     * or with status 400 (Bad Request) if the song is not valid,
     * or with status 500 (Internal Server Error) if the song couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/songs")
    @Timed
    public ResponseEntity<Song> updateSong(@RequestBody Song song) throws URISyntaxException {
        log.debug("REST request to update Song : {}", song);
        if (song.getId() == null) {
            return createSong(song);
        }
        Song result = songService.save(song);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, song.getId().toString()))
            .body(result);
    }

    /**
     * GET  /songs : get all the songs.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of songs in body
     */
    @GetMapping("/songs")
    @Timed
    public ResponseEntity<List<Song>> getAllSongs(SongCriteria criteria) {
        log.debug("REST request to get Songs by criteria: {}", criteria);
        List<Song> entityList = songQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/songs2")
    @Timed
    public ResponseEntity<List<Song>> importTopSongs() throws IOException {
        List<Song> song = napsterDTOService.importTopSongs();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     * @param id the id of the song to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/songs/{id}")
    @Timed
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        log.debug("REST request to get Song : {}", id);
        Song song = songService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }

    @GetMapping("/songs/{id}/albums")
    @Timed
    @Transactional
    public ResponseEntity<Set<Album>> getSongAlbums(@PathVariable Long id) {
        log.debug("REST request to get Song : {}", id);
        Song song = songService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song.getAlbums()));
    }

    @GetMapping("/songs/{idAlbum}/albums-song")
    @Timed
    @Transactional
    public ResponseEntity<List<Song>> getSongAlbums2(@PathVariable Long idAlbum) {
        log.debug("REST request to get Song : {}", idAlbum);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(songRepository.findByAlbumsContaining(albumRepository.findOne(idAlbum))));
    }

    /**
     * DELETE  /songs/:id : delete the "id" song.
     *
     * @param id the id of the song to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/songs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        log.debug("REST request to delete Song : {}", id);
        songService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
