
package com.dubion.service.dto.NapsterAPI.Albums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "upc",
    "shortcut",
    "href",
    "name",
    "released",
    "originallyReleased",
    "label",
    "copyright",
    "tags",
    "discCount",
    "trackCount",
    "isStreamable",
    "isExplicit",
    "isSingle",
    "accountPartner",
    "artistName",
    "contributingArtists",
    "discographies",
    "links"
})
public class Album {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("upc")
    private String upc;
    @JsonProperty("shortcut")
    private String shortcut;
    @JsonProperty("href")
    private String href;
    @JsonProperty("name")
    private String name;
    @JsonProperty("released")
    private String released;
    @JsonProperty("originallyReleased")
    private String originallyReleased;
    @JsonProperty("label")
    private String label;
    @JsonProperty("copyright")
    private String copyright;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("discCount")
    private Integer discCount;
    @JsonProperty("trackCount")
    private Integer trackCount;
    @JsonProperty("isStreamable")
    private Boolean isStreamable;
    @JsonProperty("isExplicit")
    private Boolean isExplicit;
    @JsonProperty("isSingle")
    private Boolean isSingle;
    @JsonProperty("accountPartner")
    private String accountPartner;
    @JsonProperty("artistName")
    private String artistName;
    @JsonProperty("contributingArtists")
    private ContributingArtists contributingArtists;
    @JsonProperty("discographies")
    private Discographies discographies;
    @JsonProperty("links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("upc")
    public String getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @JsonProperty("shortcut")
    public String getShortcut() {
        return shortcut;
    }

    @JsonProperty("shortcut")
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("released")
    public String getReleased() {
        return released;
    }

    @JsonProperty("released")
    public void setReleased(String released) {
        this.released = released;
    }

    @JsonProperty("originallyReleased")
    public String getOriginallyReleased() {
        return originallyReleased;
    }

    @JsonProperty("originallyReleased")
    public void setOriginallyReleased(String originallyReleased) {
        this.originallyReleased = originallyReleased;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("discCount")
    public Integer getDiscCount() {
        return discCount;
    }

    @JsonProperty("discCount")
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    @JsonProperty("trackCount")
    public Integer getTrackCount() {
        return trackCount;
    }

    @JsonProperty("trackCount")
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    @JsonProperty("isStreamable")
    public Boolean getIsStreamable() {
        return isStreamable;
    }

    @JsonProperty("isStreamable")
    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    @JsonProperty("isExplicit")
    public Boolean getIsExplicit() {
        return isExplicit;
    }

    @JsonProperty("isExplicit")
    public void setIsExplicit(Boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    @JsonProperty("isSingle")
    public Boolean getIsSingle() {
        return isSingle;
    }

    @JsonProperty("isSingle")
    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }

    @JsonProperty("accountPartner")
    public String getAccountPartner() {
        return accountPartner;
    }

    @JsonProperty("accountPartner")
    public void setAccountPartner(String accountPartner) {
        this.accountPartner = accountPartner;
    }

    @JsonProperty("artistName")
    public String getArtistName() {
        return artistName;
    }

    @JsonProperty("artistName")
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @JsonProperty("contributingArtists")
    public ContributingArtists getContributingArtists() {
        return contributingArtists;
    }

    @JsonProperty("contributingArtists")
    public void setContributingArtists(ContributingArtists contributingArtists) {
        this.contributingArtists = contributingArtists;
    }

    @JsonProperty("discographies")
    public Discographies getDiscographies() {
        return discographies;
    }

    @JsonProperty("discographies")
    public void setDiscographies(Discographies discographies) {
        this.discographies = discographies;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
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
