
package com.dubion.service.dto.TicketMasterAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classification_ {

    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("segment")
    @Expose
    private Segment_ segment;
    @SerializedName("genre")
    @Expose
    private Genre_ genre;
    @SerializedName("subGenre")
    @Expose
    private SubGenre_ subGenre;

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Segment_ getSegment() {
        return segment;
    }

    public void setSegment(Segment_ segment) {
        this.segment = segment;
    }

    public Genre_ getGenre() {
        return genre;
    }

    public void setGenre(Genre_ genre) {
        this.genre = genre;
    }

    public SubGenre_ getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(SubGenre_ subGenre) {
        this.subGenre = subGenre;
    }

}
