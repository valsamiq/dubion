package com.dubion.service;



    import com.dubion.domain.RatingAlbum;
    import com.dubion.repository.RatingAlbumRepository;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.social.google.api.plus.moments.Rating;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

/**
 * Service Implementation for managing RatingArtist.
 */
@Service
@Transactional
public class RatingAlbumService {

    private final Logger log = LoggerFactory.getLogger(RatingAlbumService.class);

    private final RatingAlbumRepository ratingAlbumRepository;

    public RatingAlbumService(RatingAlbumRepository ratingAlbumRepository) {
        this.ratingAlbumRepository = ratingAlbumRepository;
    }
    public RatingAlbum save(RatingAlbum ratingAlbum) {
        log.debug("Request to save FavouriteSong : {}", ratingAlbum);
        return ratingAlbumRepository.save(ratingAlbum);
    }
    @Transactional(readOnly = true)
    public List<RatingAlbum> findAll() {
        log.debug("Request to get all FavouriteSong");
        return ratingAlbumRepository.findAll();
    }
    @Transactional(readOnly = true)
    public RatingAlbum findOne(Long id) {
        log.debug("Request to get FavouriteSong : {}", id);
        return ratingAlbumRepository.findOne(id);
    }
    public void delete(Long id) {
        log.debug("Request to delete FavouriteSong : {}", id);
        ratingAlbumRepository.delete(id);
    }
}
