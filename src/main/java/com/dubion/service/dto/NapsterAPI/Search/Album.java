
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("shortcut")
    @Expose
    private String shortcut;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("originallyReleased")
    @Expose
    private String originallyReleased;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("isExplicit")
    @Expose
    private Boolean isExplicit;
    @SerializedName("isSingle")
    @Expose
    private Boolean isSingle;
    @SerializedName("accountPartner")
    @Expose
    private String accountPartner;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("contributingArtists")
    @Expose
    private ContributingArtists contributingArtists;
    @SerializedName("discographies")
    @Expose
    private Discographies discographies;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;

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

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
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

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getOriginallyReleased() {
        return originallyReleased;
    }

    public void setOriginallyReleased(String originallyReleased) {
        this.originallyReleased = originallyReleased;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Integer getDiscCount() {
        return discCount;
    }

    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public Boolean getIsExplicit() {
        return isExplicit;
    }

    public void setIsExplicit(Boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }

    public String getAccountPartner() {
        return accountPartner;
    }

    public void setAccountPartner(String accountPartner) {
        this.accountPartner = accountPartner;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public ContributingArtists getContributingArtists() {
        return contributingArtists;
    }

    public void setContributingArtists(ContributingArtists contributingArtists) {
        this.contributingArtists = contributingArtists;
    }

    public Discographies getDiscographies() {
        return discographies;
    }

    public void setDiscographies(Discographies discographies) {
        this.discographies = discographies;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Boolean getIsStreamable() {
        return isStreamable;
    }

    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

}
