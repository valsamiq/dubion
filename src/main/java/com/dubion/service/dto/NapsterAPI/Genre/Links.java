
package com.dubion.service.dto.NapsterAPI.Genre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("childGenres")
    @Expose
    private ChildGenres childGenres;
    @SerializedName("parentGenres")
    @Expose
    private ParentGenres parentGenres;

    public ChildGenres getChildGenres() {
        return childGenres;
    }

    public void setChildGenres(ChildGenres childGenres) {
        this.childGenres = childGenres;
    }

    public ParentGenres getParentGenres() {
        return parentGenres;
    }

    public void setParentGenres(ParentGenres parentGenres) {
        this.parentGenres = parentGenres;
    }

}
