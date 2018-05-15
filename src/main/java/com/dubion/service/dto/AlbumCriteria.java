package com.dubion.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Album entity. This class is used in AlbumResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /albums?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AlbumCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private LocalDateFilter releaseDate;

    private StringFilter photo;

    private StringFilter napsterId;

    private LongFilter bandId;

    private LongFilter genreId;

    private LongFilter ratingId;

    private LongFilter favouriteId;

    private LongFilter songId;

    public AlbumCriteria() {
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

    public LocalDateFilter getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateFilter releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StringFilter getPhoto() {
        return photo;
    }

    public void setPhoto(StringFilter photo) {
        this.photo = photo;
    }

    public StringFilter getNapsterId() {
        return napsterId;
    }

    public void setNapsterId(StringFilter napsterId) {
        this.napsterId = napsterId;
    }

    public LongFilter getBandId() {
        return bandId;
    }

    public void setBandId(LongFilter bandId) {
        this.bandId = bandId;
    }

    public LongFilter getGenreId() {
        return genreId;
    }

    public void setGenreId(LongFilter genreId) {
        this.genreId = genreId;
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

    public LongFilter getSongId() {
        return songId;
    }

    public void setSongId(LongFilter songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "AlbumCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") +
                (photo != null ? "photo=" + photo + ", " : "") +
                (napsterId != null ? "napsterId=" + napsterId + ", " : "") +
                (bandId != null ? "bandId=" + bandId + ", " : "") +
                (genreId != null ? "genreId=" + genreId + ", " : "") +
                (ratingId != null ? "ratingId=" + ratingId + ", " : "") +
                (favouriteId != null ? "favouriteId=" + favouriteId + ", " : "") +
                (songId != null ? "songId=" + songId + ", " : "") +
            "}";
    }

}
