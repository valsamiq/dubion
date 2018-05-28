package com.dubion.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dubion.domain.Album;
import com.dubion.domain.Song;
import com.dubion.service.AlbumService;
import com.dubion.repository.AlbumRepository;
import com.dubion.service.DiscogsAPI.DiscogsApiService;
import com.dubion.service.NapsterAPI.NapsterDTOService;
import com.dubion.service.dto.NapsterAPI.NapsterAlbum;
import com.dubion.service.dto.NapsterAPI.Search.Search;
import com.dubion.web.rest.errors.BadRequestAlertException;
import com.dubion.web.rest.util.HeaderUtil;
import com.dubion.service.dto.AlbumCriteria;
import com.dubion.service.AlbumQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Album.
 */
@RestController
@RequestMapping("/api")
public class AlbumResource {

    private final Logger log = LoggerFactory.getLogger(AlbumResource.class);

    private static final String ENTITY_NAME = "album";

    private final AlbumRepository albumRepository;

    private final AlbumService albumService;

    private final AlbumQueryService albumQueryService;

    private final NapsterDTOService napsterDTOService;

    @Autowired
    private DiscogsApiService discogsApiService;

    public AlbumResource(AlbumRepository albumRepository, AlbumService albumService, AlbumQueryService albumQueryService, NapsterDTOService napsterDTOService) {
        this.albumRepository = albumRepository;
        this.albumService = albumService;
        this.albumQueryService = albumQueryService;
        this.napsterDTOService = napsterDTOService;
    }

    /**
     * POST  /albums : Create a new album.
     *
     * @param album the album to create
     * @return the ResponseEntity with status 201 (Created) and with body the new album, or with status 400 (Bad Request) if the album has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/albums")
    @Timed
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) throws URISyntaxException {
        log.debug("REST request to save Album : {}", album);
        if (album.getId() != null) {
            throw new BadRequestAlertException("A new album cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Album result = albumService.save(album);
        return ResponseEntity.created(new URI("/api/album/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /albums : Updates an existing album.
     *
     * @param album the album to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated album,
     * or with status 400 (Bad Request) if the album is not valid,
     * or with status 500 (Internal Server Error) if the album couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/albums")
    @Timed
    public ResponseEntity<Album> updateAlbum(@RequestBody Album album) throws URISyntaxException {
        log.debug("REST request to update Album : {}", album);
        if (album.getId() == null) {
            return createAlbum(album);
        }
        Album result = albumService.save(album);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, album.getId().toString()))
            .body(result);
    }

    /**
     * GET  /albums : get all the albums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of albums in body
     */
    @GetMapping("/albums/name/{name}/page/{page}")
    @Timed
    public ResponseEntity<List<Album>> getAllAlbumsPageable(@PathVariable String name ,@PathVariable int page) {
        log.debug("REST request to get all Albums by Criteria {}");
        List<Album> entityList = albumRepository.findByNameContaining(name,new PageRequest(page,10));

        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  songs from album Id, pageble /kelvin/
     */
    @GetMapping("/albums/{id}/page/{page}")
    @Timed
    public ResponseEntity<List<Song>> getAllSongsFromAlbumPageable(@PathVariable long id ,@PathVariable int page) {
        log.debug("REST request to get all Albums by Criteria {}");
        List<Song> entityList = albumRepository.findSongsByAlbumPageble(id, new PageRequest(page,10));

        return ResponseEntity.ok().body(entityList);
    }


    /** Si la cago aqui ta la copia original
     * GET  /albums : get all the albums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of albums in bo*/

    @GetMapping("/albums")
    @Timed
    public ResponseEntity<List<Album>> getAllAlbums(AlbumCriteria criteria) {
        log.debug("REST request to get all Albums by Criteria {}", criteria);
        List<Album> entityList = albumQueryService.findByCriteria(criteria);

        return ResponseEntity.ok().body(entityList);
    }


    /**
     * GET  /albums/:id : get the "id" album.
     *
     * @param id the id of the album to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the album, or with status 404 (Not Found)
     */
    @GetMapping("/albums/{id}")
    @Timed
    public ResponseEntity<Album> getAlbum(@PathVariable Long id) {
        log.debug("REST request to get Album : {}", id);
        Album album = albumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(album));
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/albums/top")
    @Timed
    public ResponseEntity<NapsterAlbum> getTopAlbumNap() {
        NapsterAlbum album = napsterDTOService.getTopAlbumNap();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(album));
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/albums/top2")
    @Timed
    public ResponseEntity<List<Album>> importTopAlbums() throws IOException {
        List<Album> song = napsterDTOService.importTopAlbum();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }
    @GetMapping("/albums/search/{albumName}")
    public List<com.dubion.service.dto.NapsterAPI.Search.Album> getAlbumSearch(@PathVariable String albumName){
        Search search = napsterDTOService.searchAndImportAlbums(albumName);

        search.getSearch().getData().getAlbums().
            stream().
            filter(album -> album != null)
            .forEach(
            album -> album.setIdDubion(albumRepository.findByNapsterId(album.getId()).getId())
            );

        return search.getSearch().getData().getAlbums();
    }
    @GetMapping("/albums/band/{bandId}")
    public List<Album> getAlbumBand(@PathVariable Long bandId){

        return albumRepository.findByBandId(bandId);
    }
    /**
     * GET  /songs/:id : get the "id" song.
     *
     *
     * @return the ResponseEntity with status 200 (OK) and with body the song, or with status 404 (Not Found)
     */
    @GetMapping("/albumsNew")
    @Timed
    public ResponseEntity<List<Album>> importAlbumNew() throws IOException {
        List<Album> song = napsterDTOService.importAlbumNew();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(song));
    }
    /**
     * DELETE  /albums/:id : delete the "id" album.
     *
     * @param id the id of the album to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/albums/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        log.debug("REST request to delete Album : {}", id);
        albumService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/albums/by-name/{albumName}")
    public List<Album> getAlbumByName(@PathVariable String albumName){
        return albumRepository.findByNameContaining(albumName);
    }
}
