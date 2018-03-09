package com.dubion.service.dto.NapsterAPI;

import com.dubion.service.dto.NapsterAPI.Albums.Album;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NapsterAlbum {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("albums")
    @Expose
    private List<Album> albums = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setTracks(List<Album> tracks) {
        this.albums = albums;
    }
}
