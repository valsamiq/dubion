
package com.dubion.service.dto.NapsterAPI.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("search")
    @Expose
    private Search_ search;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Search_ getSearch() {return search;}

    public void setSearch(Search_ search) {
        this.search = search;
    }

}
