package com.dubion.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;


public class FavouriteSongCriteria implements Serializable{
    private static final long serialVersionUID = 1L;

    private  LongFilter id;

    private BooleanFilter liked;

    private LocalDateFilter date;

    public FavouriteSongCriteria() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BooleanFilter getLiked() {
        return liked;
    }

    public void setLiked(BooleanFilter liked) {
        this.liked = liked;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FavouriteSongCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (liked != null ? "liked=" + liked + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            '}';
    }
}
