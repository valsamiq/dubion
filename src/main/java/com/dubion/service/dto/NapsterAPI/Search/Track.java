
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("disc")
    @Expose
    private Integer disc;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("playbackSeconds")
    @Expose
    private Integer playbackSeconds;
    @SerializedName("isExplicit")
    @Expose
    private Boolean isExplicit;
    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isrc")
    @Expose
    private String isrc;
    @SerializedName("shortcut")
    @Expose
    private String shortcut;
    @SerializedName("blurbs")
    @Expose
    private List<Object> blurbs = null;
    @SerializedName("artistId")
    @Expose
    private String artistId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("albumName")
    @Expose
    private String albumName;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;
    @SerializedName("albumId")
    @Expose
    private String albumId;
    @SerializedName("contributors")
    @Expose
    private Contributors contributors;
    @SerializedName("links")
    @Expose
    private Links__ links;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getDisc() {
        return disc;
    }

    public void setDisc(Integer disc) {
        this.disc = disc;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPlaybackSeconds() {
        return playbackSeconds;
    }

    public void setPlaybackSeconds(Integer playbackSeconds) {
        this.playbackSeconds = playbackSeconds;
    }

    public Boolean getIsExplicit() {
        return isExplicit;
    }

    public void setIsExplicit(Boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public Boolean getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public List<Object> getBlurbs() {
        return blurbs;
    }

    public void setBlurbs(List<Object> blurbs) {
        this.blurbs = blurbs;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Contributors getContributors() {
        return contributors;
    }

    public void setContributors(Contributors contributors) {
        this.contributors = contributors;
    }

    public Links__ getLinks() {
        return links;
    }

    public void setLinks(Links__ links) {
        this.links = links;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

}
