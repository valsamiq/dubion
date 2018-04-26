
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discographies {

    @SerializedName("compilations")
    @Expose
    private List<String> compilations = null;

    public List<String> getCompilations() {
        return compilations;
    }

    public void setCompilations(List<String> compilations) {
        this.compilations = compilations;
    }

}
