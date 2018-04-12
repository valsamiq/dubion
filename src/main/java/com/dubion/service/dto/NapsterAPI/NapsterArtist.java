package com.dubion.service.dto.NapsterAPI;

import com.dubion.service.dto.NapsterAPI.Artist.Artist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NapsterArtist {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("artists")
    @Expose
    private List<Artist> artist = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Artist> getArtists() {return artist;}

    public void setTracks(List<Artist> tracks) {
        this.artist = artist;
    }
}
