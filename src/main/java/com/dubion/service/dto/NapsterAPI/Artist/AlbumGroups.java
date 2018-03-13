
package com.dubion.service.dto.NapsterAPI.Artist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumGroups {

    @SerializedName("singlesAndEPs")
    @Expose
    private List<String> singlesAndEPs = null;
    @SerializedName("compilations")
    @Expose
    private List<String> compilations = null;
    @SerializedName("others")
    @Expose
    private List<String> others = null;
    @SerializedName("main")
    @Expose
    private List<String> main = null;

    public List<String> getSinglesAndEPs() {
        return singlesAndEPs;
    }

    public void setSinglesAndEPs(List<String> singlesAndEPs) {
        this.singlesAndEPs = singlesAndEPs;
    }

    public List<String> getCompilations() {
        return compilations;
    }

    public void setCompilations(List<String> compilations) {
        this.compilations = compilations;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public List<String> getMain() {
        return main;
    }

    public void setMain(List<String> main) {
        this.main = main;
    }

}
