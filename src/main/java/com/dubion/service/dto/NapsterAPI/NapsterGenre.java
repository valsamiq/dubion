package com.dubion.service.dto.NapsterAPI;

import com.dubion.service.dto.NapsterAPI.Genre.Genre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NapsterGenre {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("genres")
    @Expose
    private List<Genre> genre = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Genre> getGenre() {return genre;}

    public void setTracks(List<Genre> tracks) {
        this.genre = genre;
    }
}
