
package com.dubion.service.dto.GoogleMaps.Geolocation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoogleMapsGeolocationDTO {

    @SerializedName("homeMobileCountryCode")
    @Expose
    private Integer homeMobileCountryCode;
    @SerializedName("homeMobileNetworkCode")
    @Expose
    private Integer homeMobileNetworkCode;
    @SerializedName("radioType")
    @Expose
    private String radioType;
    @SerializedName("carrier")
    @Expose
    private String carrier;
    @SerializedName("considerIp")
    @Expose
    private String considerIp;
    @SerializedName("cellTowers")
    @Expose
    private List<CellTower> cellTowers = null;
    @SerializedName("wifiAccessPoints")
    @Expose
    private List<WifiAccessPoint> wifiAccessPoints = null;

    public Integer getHomeMobileCountryCode() {
        return homeMobileCountryCode;
    }

    public void setHomeMobileCountryCode(Integer homeMobileCountryCode) {
        this.homeMobileCountryCode = homeMobileCountryCode;
    }

    public Integer getHomeMobileNetworkCode() {
        return homeMobileNetworkCode;
    }

    public void setHomeMobileNetworkCode(Integer homeMobileNetworkCode) {
        this.homeMobileNetworkCode = homeMobileNetworkCode;
    }

    public String getRadioType() {
        return radioType;
    }

    public void setRadioType(String radioType) {
        this.radioType = radioType;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getConsiderIp() {
        return considerIp;
    }

    public void setConsiderIp(String considerIp) {
        this.considerIp = considerIp;
    }

    public List<CellTower> getCellTowers() {
        return cellTowers;
    }

    public void setCellTowers(List<CellTower> cellTowers) {
        this.cellTowers = cellTowers;
    }

    public List<WifiAccessPoint> getWifiAccessPoints() {
        return wifiAccessPoints;
    }

    public void setWifiAccessPoints(List<WifiAccessPoint> wifiAccessPoints) {
        this.wifiAccessPoints = wifiAccessPoints;
    }

}
