
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortcut")
    @Expose
    private String shortcut;
    @SerializedName("amg")
    @Expose
    private String amg;
    @SerializedName("blurbs")
    @Expose
    private List<Object> blurbs = null;
    @SerializedName("albumGroups")
    @Expose
    private AlbumGroups albumGroups;
    @SerializedName("links")
    @Expose
    private Links_ links;

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getAmg() {
        return amg;
    }

    public void setAmg(String amg) {
        this.amg = amg;
    }

    public List<Object> getBlurbs() {
        return blurbs;
    }

    public void setBlurbs(List<Object> blurbs) {
        this.blurbs = blurbs;
    }

    public AlbumGroups getAlbumGroups() {
        return albumGroups;
    }

    public void setAlbumGroups(AlbumGroups albumGroups) {
        this.albumGroups = albumGroups;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}
