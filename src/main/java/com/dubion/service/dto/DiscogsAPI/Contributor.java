
package com.dubion.service.dto.DiscogsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("resource_url")
    @Expose
    private String resourceUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

}
