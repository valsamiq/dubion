
package com.dubion.service.dto.GoogleMaps.Geolocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CellTower {

    @SerializedName("cellId")
    @Expose
    private Integer cellId;
    @SerializedName("locationAreaCode")
    @Expose
    private Integer locationAreaCode;
    @SerializedName("mobileCountryCode")
    @Expose
    private Integer mobileCountryCode;
    @SerializedName("mobileNetworkCode")
    @Expose
    private Integer mobileNetworkCode;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("signalStrength")
    @Expose
    private Integer signalStrength;
    @SerializedName("timingAdvance")
    @Expose
    private Integer timingAdvance;

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getLocationAreaCode() {
        return locationAreaCode;
    }

    public void setLocationAreaCode(Integer locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public Integer getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(Integer mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public Integer getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(Integer mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(Integer signalStrength) {
        this.signalStrength = signalStrength;
    }

    public Integer getTimingAdvance() {
        return timingAdvance;
    }

    public void setTimingAdvance(Integer timingAdvance) {
        this.timingAdvance = timingAdvance;
    }

}
