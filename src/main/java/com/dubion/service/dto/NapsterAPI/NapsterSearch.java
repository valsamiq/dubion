package com.dubion.service.dto.NapsterAPI;

import com.dubion.service.dto.NapsterAPI.Search.Search;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NapsterSearch {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("search")
    @Expose
    private List<Search> searchs = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Search> getAlbums() {
        return searchs;
    }

    public void setTracks(List<Search> tracks) {
        this.searchs = searchs;
    }
}
