package com.dubion.service.dto;


import com.dubion.domain.Band;

public class StatsBandRating {
    private Band band;
    private Integer max;
    private Integer min;
    private Double avg;

    public StatsBandRating(Band band, Integer max, Integer min, Double avg) {
        this.band = band;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

}
