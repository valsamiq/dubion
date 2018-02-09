
package com.dubion.service.dto.DiscogsAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscogsApiDTO {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("series")
    @Expose
    private List<Object> series = null;
    @SerializedName("labels")
    @Expose
    private List<Label> labels = null;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("community")
    @Expose
    private Community community;
    @SerializedName("artists")
    @Expose
    private List<Artist> artists = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("format_quantity")
    @Expose
    private Integer formatQuantity;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("num_for_sale")
    @Expose
    private Integer numForSale;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date_changed")
    @Expose
    private String dateChanged;
    @SerializedName("master_id")
    @Expose
    private Integer masterId;
    @SerializedName("lowest_price")
    @Expose
    private Double lowestPrice;
    @SerializedName("styles")
    @Expose
    private List<String> styles = null;
    @SerializedName("released_formatted")
    @Expose
    private String releasedFormatted;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;
    @SerializedName("estimated_weight")
    @Expose
    private Integer estimatedWeight;
    @SerializedName("master_url")
    @Expose
    private String masterUrl;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("extraartists")
    @Expose
    private List<Extraartist> extraartists = null;
    @SerializedName("tracklist")
    @Expose
    private List<Tracklist> tracklist = null;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier> identifiers = null;
    @SerializedName("companies")
    @Expose
    private List<Company> companies = null;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("resource_url")
    @Expose
    private String resourceUrl;
    @SerializedName("data_quality")
    @Expose
    private String dataQuality;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Object> getSeries() {
        return series;
    }

    public void setSeries(List<Object> series) {
        this.series = series;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getFormatQuantity() {
        return formatQuantity;
    }

    public void setFormatQuantity(Integer formatQuantity) {
        this.formatQuantity = formatQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getNumForSale() {
        return numForSale;
    }

    public void setNumForSale(Integer numForSale) {
        this.numForSale = numForSale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(String dateChanged) {
        this.dateChanged = dateChanged;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public String getReleasedFormatted() {
        return releasedFormatted;
    }

    public void setReleasedFormatted(String releasedFormatted) {
        this.releasedFormatted = releasedFormatted;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public Integer getEstimatedWeight() {
        return estimatedWeight;
    }

    public void setEstimatedWeight(Integer estimatedWeight) {
        this.estimatedWeight = estimatedWeight;
    }

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Extraartist> getExtraartists() {
        return extraartists;
    }

    public void setExtraartists(List<Extraartist> extraartists) {
        this.extraartists = extraartists;
    }

    public List<Tracklist> getTracklist() {
        return tracklist;
    }

    public void setTracklist(List<Tracklist> tracklist) {
        this.tracklist = tracklist;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getDataQuality() {
        return dataQuality;
    }

    public void setDataQuality(String dataQuality) {
        this.dataQuality = dataQuality;
    }

}
