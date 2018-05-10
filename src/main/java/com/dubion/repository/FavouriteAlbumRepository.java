package com.dubion.repository;

import com.dubion.domain.Album;
import com.dubion.domain.FavouriteAlbum;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the FavouriteAlbum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FavouriteAlbumRepository extends JpaRepository<FavouriteAlbum, Long>, JpaSpecificationExecutor<FavouriteAlbum> {

    @Query("select favourite_album from FavouriteAlbum favourite_album where favourite_album.user.login = ?#{principal.username}")
    List<FavouriteAlbum> findByUserIsCurrentUser();

    Optional<FavouriteAlbum> findByAlbumAndUserLogin(Album album, String login);
    Optional<FavouriteAlbum> findByAlbumIdAndUserLogin(Long albumId, String login);

    @Query("select favourite_album.album from FavouriteAlbum favourite_album where favourite_album.liked = true AND favourite_album.user.login=:login")
    List<Album> findByFavoriteAlbum(@Param("login") String login);

}
