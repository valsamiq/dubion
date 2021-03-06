
package com.dubion.service.dto.DiscogsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("items")
    @Expose
    private Integer items;

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

}
