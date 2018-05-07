
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

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

    public String getAlbumId() {
        return albumId;
    }

    public void setName(String name) { this.name = name;}

    public void setHref(String href) {
        this.href = href;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

}
