
package com.dubion.service.dto.GoogleMaps.Geolocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WifiAccessPoint {

    @SerializedName("macAddress")
    @Expose
    private String macAddress;
    @SerializedName("signalStrength")
    @Expose
    private Integer signalStrength;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("channel")
    @Expose
    private Integer channel;
    @SerializedName("signalToNoiseRatio")
    @Expose
    private Integer signalToNoiseRatio;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(Integer signalStrength) {
        this.signalStrength = signalStrength;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getSignalToNoiseRatio() {
        return signalToNoiseRatio;
    }

    public void setSignalToNoiseRatio(Integer signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
    }

}
