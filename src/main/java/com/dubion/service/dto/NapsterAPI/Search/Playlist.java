
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("favoriteCount")
    @Expose
    private Integer favoriteCount;
    @SerializedName("freePlayCompliant")
    @Expose
    private Boolean freePlayCompliant;
    @SerializedName("links")
    @Expose
    private Links___ links;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFreePlayCompliant() {
        return freePlayCompliant;
    }

    public void setFreePlayCompliant(Boolean freePlayCompliant) {
        this.freePlayCompliant = freePlayCompliant;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
        this.links = links;
    }

}
