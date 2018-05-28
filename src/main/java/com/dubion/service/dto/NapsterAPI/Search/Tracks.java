
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    private long idDubion;

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("albumId")
    @Expose
    private String albumId;


    private String name;

    public String getName(){ return name; }

    public String getHref() {
        return href;
    }

    public String getAlbumId() {return albumId;}

    public void setName(String name) { this.name = name;}

    public void setHref(String href) {
        this.href = href;
    }

    public long getIdDubion() {
        return idDubion;
    }

    public void setIdDubion(long idDubion) {
        this.idDubion = idDubion;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

}
