
package com.dubion.service.dto.NapsterAPI;

import com.dubion.domain.Genre;
import com.dubion.service.dto.NapsterAPI.Albums.Album;
import com.dubion.service.dto.NapsterAPI.Artist.Artist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("artists")
    @Expose
    private Artist artist;
    @SerializedName("albums")
    @Expose
    private Album albums;
    @SerializedName("composers")
    @Expose
    private Composers composers;
    @SerializedName("genres")
    @Expose
    private Genre genres;
    @SerializedName("tags")
    @Expose
    private Tags tags;

    public Artist getArtists() {
        return artist;
    }

    public void setArtists(Artist artists) {
        this.artist = artists;
    }

    public Album getAlbums() {
        return albums;
    }

    public void setAlbums(Album albums) {
        this.albums = albums;
    }

    public Composers getComposers() {
        return composers;
    }

    public void setComposers(Composers composers) {
        this.composers = composers;
    }

    public Genre getGenres() {
        return genres;
    }

    public void setGenres(Genre genres) {
        this.genres = genres;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

}
