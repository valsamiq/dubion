
package com.dubion.service.dto.TiquetMasterAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links_ {

    @SerializedName("self")
    @Expose
    private Self_ self;
    @SerializedName("attractions")
    @Expose
    private List<Attraction> attractions = null;
    @SerializedName("venues")
    @Expose
    private List<Venue> venues = null;

    public Self_ getSelf() {
        return self;
    }

    public void setSelf(Self_ self) {
        this.self = self;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

}
