
package com.dubion.service.dto.TiquetMasterAPI;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attraction_ {

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
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("images")
    @Expose
    private List<Image_> images = null;
    @SerializedName("classifications")
    @Expose
    private List<Classification_> classifications = null;
    @SerializedName("_links")
    @Expose
    private Links___ links;

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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<Image_> getImages() {
        return images;
    }

    public void setImages(List<Image_> images) {
        this.images = images;
    }

    public List<Classification_> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification_> classifications) {
        this.classifications = classifications;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
        this.links = links;
    }

}
