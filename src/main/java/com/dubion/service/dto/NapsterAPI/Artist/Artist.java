
package com.dubion.service.dto.NapsterAPI.Artist;

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
    private List<String> blurbs = null;
    @SerializedName("bios")
    @Expose
    private List<Bio> bios = null;
    @SerializedName("albumGroups")
    @Expose
    private AlbumGroups albumGroups;
    @SerializedName("links")
    @Expose
    private Links links;

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

    public List<String> getBlurbs() {
        return blurbs;
    }

    public void setBlurbs(List<String> blurbs) {
        this.blurbs = blurbs;
    }

    public List<Bio> getBios() {
        return bios;
    }

    public void setBios(List<Bio> bios) {
        this.bios = bios;
    }

    public AlbumGroups getAlbumGroups() {return albumGroups;}

    public void setAlbumGroups(AlbumGroups albumGroups) {
        this.albumGroups = albumGroups;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
