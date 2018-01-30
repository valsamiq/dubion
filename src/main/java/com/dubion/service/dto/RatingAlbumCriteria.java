package com.dubion.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;

public class RatingAlbumCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter date;

    private IntegerFilter rating;

    public RatingAlbumCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getDate() {
        return date;
    }

    public void setDate(ZonedDateTimeFilter date) {
        this.date = date;
    }

    public IntegerFilter getRating() {
        return rating;
    }

    public void setRating(IntegerFilter rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "RatingAlbumCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (rating != null ? "rating=" + rating + ", " : "") +
            "}";
    }
}
