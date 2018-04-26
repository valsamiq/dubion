
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links___ {

    @SerializedName("members")
    @Expose
    private Members members;
    @SerializedName("tracks")
    @Expose
    private Tracks_ tracks;
    @SerializedName("tags")
    @Expose
    private Tags_ tags;
    @SerializedName("sampleArtists")
    @Expose
    private SampleArtists sampleArtists;

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public Tracks_ getTracks() {
        return tracks;
    }

    public void setTracks(Tracks_ tracks) {
        this.tracks = tracks;
    }

    public Tags_ getTags() {
        return tags;
    }

    public void setTags(Tags_ tags) {
        this.tags = tags;
    }

    public SampleArtists getSampleArtists() {
        return sampleArtists;
    }

    public void setSampleArtists(SampleArtists sampleArtists) {
        this.sampleArtists = sampleArtists;
    }

}
