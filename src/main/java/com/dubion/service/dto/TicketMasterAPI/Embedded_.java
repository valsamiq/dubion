
package com.dubion.service.dto.TicketMasterAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded_ {

    @SerializedName("venues")
    @Expose
    private List<Venue_> venues = null;
    @SerializedName("attractions")
    @Expose
    private List<Attraction_> attractions = null;

    public List<Venue_> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue_> venues) {
        this.venues = venues;
    }

    public List<Attraction_> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction_> attractions) {
        this.attractions = attractions;
    }

}
