package com.dubion.service.dto.NapsterAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributors {

    @SerializedName("composer")
    @Expose
    private String composer;
    @SerializedName("nonPrimary")
    @Expose
    private String nonPrimary;
    @SerializedName("primaryArtist")
    @Expose
    private String primaryArtist;
    @SerializedName("engineer")
    @Expose
    private String engineer;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("featuredPerformer")
    @Expose
    private String featuredPerformer;
    @SerializedName("guestVocals")
    @Expose
    private String guestVocals;
    @SerializedName("guestMusician")
    @Expose
    private String guestMusician;

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getNonPrimary() {
        return nonPrimary;
    }

    public void setNonPrimary(String nonPrimary) {
        this.nonPrimary = nonPrimary;
    }

    public String getPrimaryArtist() {
        return primaryArtist;
    }

    public void setPrimaryArtist(String primaryArtist) {
        this.primaryArtist = primaryArtist;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getFeaturedPerformer() {
        return featuredPerformer;
    }

    public void setFeaturedPerformer(String featuredPerformer) {
        this.featuredPerformer = featuredPerformer;
    }

    public String getGuestVocals() {
        return guestVocals;
    }

    public void setGuestVocals(String guestVocals) {
        this.guestVocals = guestVocals;
    }

    public String getGuestMusician() {
        return guestMusician;
    }

    public void setGuestMusician(String guestMusician) {
        this.guestMusician = guestMusician;
    }

}
