
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;

import com.dubion.domain.Song;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("albums")
    @Expose
    private List<Album> albums = null;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @SerializedName("tracks")
    @Expose
    private List<Tracks> tracks = null;

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) { this.tracks = tracks; }

    @SerializedName("artist")
    @Expose
    private List<Artists> artists = null;

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

}
