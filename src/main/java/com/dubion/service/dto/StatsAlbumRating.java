package com.dubion.service.dto;

import com.dubion.domain.Album;

public class StatsAlbumRating {

    private Album album;
    private Integer max;
    private Integer min;
    private Double avg;

    public StatsAlbumRating(Album album, Double avg, Integer max, Integer min) {
        this.album = album;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
