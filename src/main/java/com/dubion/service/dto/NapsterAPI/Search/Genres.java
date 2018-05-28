
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genres {

    private long idDubion;

    @SerializedName("ids")
    @Expose
    private List<String> ids = null;
    @SerializedName("href")
    @Expose
    private String href;

    public long getIdDubion() {
        return idDubion;
    }

    public void setIdDubion(long idDubion) {
        this.idDubion = idDubion;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
