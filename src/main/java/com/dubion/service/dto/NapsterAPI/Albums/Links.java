
package com.dubion.service.dto.NapsterAPI.Albums;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "images",
    "tracks",
    "posts",
    "artists",
    "genres"
})
public class Links {

    @JsonProperty("images")
    private Images images;
    @JsonProperty("tracks")
    private Tracks tracks;
    @JsonProperty("posts")
    private Posts posts;
    @JsonProperty("artists")
    private Artists artists;
    @JsonProperty("genres")
    private Genres genres;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("images")
    public Images getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Images images) {
        this.images = images;
    }

    @JsonProperty("tracks")
    public Tracks getTracks() {
        return tracks;
    }

    @JsonProperty("tracks")
    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @JsonProperty("posts")
    public Posts getPosts() {
        return posts;
    }

    @JsonProperty("posts")
    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    @JsonProperty("artists")
    public Artists getArtists() {
        return artists;
    }

    @JsonProperty("artists")
    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    @JsonProperty("genres")
    public Genres getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
