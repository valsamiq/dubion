
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links_ {

    @SerializedName("albums")
    @Expose
    private Albums albums;
    @SerializedName("images")
    @Expose
    private Images_ images;
    @SerializedName("posts")
    @Expose
    private Posts_ posts;
    @SerializedName("topTracks")
    @Expose
    private TopTracks topTracks;
    @SerializedName("genres")
    @Expose
    private Genres_ genres;
    @SerializedName("stations")
    @Expose
    private Stations stations;
    @SerializedName("relatedProjects")
    @Expose
    private RelatedProjects relatedProjects;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public Images_ getImages() {
        return images;
    }

    public void setImages(Images_ images) {
        this.images = images;
    }

    public Posts_ getPosts() {
        return posts;
    }

    public void setPosts(Posts_ posts) {
        this.posts = posts;
    }

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public void setTopTracks(TopTracks topTracks) {
        this.topTracks = topTracks;
    }

    public Genres_ getGenres() {
        return genres;
    }

    public void setGenres(Genres_ genres) {
        this.genres = genres;
    }

    public Stations getStations() {
        return stations;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

    public RelatedProjects getRelatedProjects() {
        return relatedProjects;
    }

    public void setRelatedProjects(RelatedProjects relatedProjects) {
        this.relatedProjects = relatedProjects;
    }

}
