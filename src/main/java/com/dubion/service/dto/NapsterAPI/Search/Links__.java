
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links__ {

    @SerializedName("artists")
    @Expose
    private Artists_ artists;
    @SerializedName("albums")
    @Expose
    private Albums_ albums;
    @SerializedName("genres")
    @Expose
    private Genres__ genres;
    @SerializedName("tags")
    @Expose
    private Tags tags;

    public Artists_ getArtists() {
        return artists;
    }

    public void setArtists(Artists_ artists) {
        this.artists = artists;
    }

    public Albums_ getAlbums() {
        return albums;
    }

    public void setAlbums(Albums_ albums) {
        this.albums = albums;
    }

    public Genres__ getGenres() {
        return genres;
    }

    public void setGenres(Genres__ genres) {
        this.genres = genres;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

}
