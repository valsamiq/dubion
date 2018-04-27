
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;
    @SerializedName("posts")
    @Expose
    private Posts posts;
    @SerializedName("artists")
    @Expose
    private Artists artists;
    @SerializedName("genres")
    @Expose
    private Genres genres;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

}
