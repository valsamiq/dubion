
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributors {

    @SerializedName("primaryArtist")
    @Expose
    private String primaryArtist;

    public String getPrimaryArtist() {
        return primaryArtist;
    }

    public void setPrimaryArtist(String primaryArtist) {
        this.primaryArtist = primaryArtist;
    }

}
