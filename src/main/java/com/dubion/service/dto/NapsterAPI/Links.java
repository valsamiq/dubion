
package com.dubion.service.dto.NapsterAPI;

import com.dubion.service.dto.NapsterAPI.Albums.Album;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("artists")
    @Expose
    private Artists artists;
    @SerializedName("albums")
    @Expose
    private Album albums;
    @SerializedName("composers")
    @Expose
    private Composers composers;
    @SerializedName("genres")
    @Expose
    private Genres genres;
    @SerializedName("tags")
    @Expose
    private Tags tags;

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
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

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

}
