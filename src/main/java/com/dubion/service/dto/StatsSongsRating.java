package com.dubion.service.dto;


import com.dubion.domain.Song;

public class StatsSongsRating {
    private Song song;
    private Integer max;
    private Integer min;
    private Double avg;

    public StatsSongsRating(Song song, Double avg,Integer max, Integer min) {
        this.song = song;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
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
