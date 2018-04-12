
package com.dubion.service.dto.TiquetMasterAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketMasterAPI {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("page")
    @Expose
    private Page page;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
