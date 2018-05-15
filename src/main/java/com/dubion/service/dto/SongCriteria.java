package com.dubion.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Song entity. This class is used in SongResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /songs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SongCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter url;

    private StringFilter napsterId;

    private LongFilter albumId;

    private LongFilter ratingId;

    private LongFilter favouriteId;

    public SongCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getNapsterId() {
        return napsterId;
    }

    public void setNapsterId(StringFilter napsterId) {
        this.napsterId = napsterId;
    }

    public LongFilter getAlbumId() {
        return albumId;
    }

    public void setAlbumId(LongFilter albumId) {
        this.albumId = albumId;
    }

    public LongFilter getRatingId() {
        return ratingId;
    }

    public void setRatingId(LongFilter ratingId) {
        this.ratingId = ratingId;
    }

    public LongFilter getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(LongFilter favouriteId) {
        this.favouriteId = favouriteId;
    }

    @Override
    public String toString() {
        return "SongCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (napsterId != null ? "napsterId=" + napsterId + ", " : "") +
                (albumId != null ? "albumId=" + albumId + ", " : "") +
                (ratingId != null ? "ratingId=" + ratingId + ", " : "") +
                (favouriteId != null ? "favouriteId=" + favouriteId + ", " : "") +
            "}";
    }

}
