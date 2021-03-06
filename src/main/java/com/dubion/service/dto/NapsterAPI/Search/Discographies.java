
package com.dubion.service.dto.NapsterAPI.Search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discographies {

    @SerializedName("compilations")
    @Expose
    private List<String> compilations = null;
    @SerializedName("singlesAndEPs")
    @Expose
    private List<String> singlesAndEPs = null;
    @SerializedName("main")
    @Expose
    private List<String> main = null;

    public List<String> getCompilations() {
        return compilations;
    }

    public void setCompilations(List<String> compilations) {
        this.compilations = compilations;
    }

    public List<String> getSinglesAndEPs() {
        return singlesAndEPs;
    }

    public void setSinglesAndEPs(List<String> singlesAndEPs) {
        this.singlesAndEPs = singlesAndEPs;
    }

    public List<String> getMain() {
        return main;
    }

    public void setMain(List<String> main) {
        this.main = main;
    }

}
