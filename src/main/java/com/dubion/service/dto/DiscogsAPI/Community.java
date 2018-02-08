
package com.dubion.service.dto.DiscogsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Community {

    @SerializedName("want")
    @Expose
    private Integer want;
    @SerializedName("have")
    @Expose
    private Integer have;

    public Integer getWant() {
        return want;
    }

    public void setWant(Integer want) {
        this.want = want;
    }

    public Integer getHave() {
        return have;
    }

    public void setHave(Integer have) {
        this.have = have;
    }

}
