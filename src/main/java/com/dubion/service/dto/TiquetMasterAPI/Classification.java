
package com.dubion.service.dto.TiquetMasterAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classification {

    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("segment")
    @Expose
    private Segment segment;
    @SerializedName("genre")
    @Expose
    private Genre genre;
    @SerializedName("subGenre")
    @Expose
    private SubGenre subGenre;

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public SubGenre getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(SubGenre subGenre) {
        this.subGenre = subGenre;
    }

}
