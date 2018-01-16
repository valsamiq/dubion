package com.dubion.service.dto;


import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.Filter;


/**
 * Criteria class for the FavouriteAlbum entity. This class is used in FavouriteAlbumResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /favouriteAlbum?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FavouriteAlbumCriteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BooleanFilter liked;

    private LocalDateFilter date;

    private LongFilter userId;

    private LongFilter albumId;

    public FavouriteAlbumCriteria() {
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

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getAlbumId() {
        return albumId;
    }

    public void setAlbumId(LongFilter albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "FavouriteAlbumCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (liked != null ? "liked=" + liked + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (albumId != null ? "albumId=" + albumId + ", " : "")+
            '}';
    }
}
