
package com.dubion.service.dto.TiquetMasterAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("test")
    @Expose
    private Boolean test;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("sales")
    @Expose
    private Sales sales;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("classifications")
    @Expose
    private List<Classification> classifications = null;
    @SerializedName("promoter")
    @Expose
    private Promoter promoter;
    @SerializedName("_links")
    @Expose
    private Links_ links;
    @SerializedName("_embedded")
    @Expose
    private Embedded_ embedded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public Promoter getPromoter() {
        return promoter;
    }

    public void setPromoter(Promoter promoter) {
        this.promoter = promoter;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

    public Embedded_ getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded_ embedded) {
        this.embedded = embedded;
    }

}
