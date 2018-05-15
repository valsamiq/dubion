package com.dubion.service.dto;

import java.io.Serializable;
import com.dubion.domain.enumeration.Status;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Band entity. This class is used in BandResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /bands?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BandCriteria implements Serializable {
    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private LocalDateFilter birthDate;

    private StringFilter bio;

    private StatusFilter status;

    private StringFilter photo;

    private StringFilter napsterId;

    private LongFilter countryId;

    private LongFilter labelId;

    private LongFilter socialId;

    private LongFilter genreId;

    private LongFilter ratingId;

    private LongFilter favouriteId;

    private LongFilter artistId;

    public BandCriteria() {
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

    public LocalDateFilter getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateFilter birthDate) {
        this.birthDate = birthDate;
    }

    public StringFilter getBio() {
        return bio;
    }

    public void setBio(StringFilter bio) {
        this.bio = bio;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
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

    public LongFilter getCountryId() {
        return countryId;
    }

    public void setCountryId(LongFilter countryId) {
        this.countryId = countryId;
    }

    public LongFilter getLabelId() {
        return labelId;
    }

    public void setLabelId(LongFilter labelId) {
        this.labelId = labelId;
    }

    public LongFilter getSocialId() {
        return socialId;
    }

    public void setSocialId(LongFilter socialId) {
        this.socialId = socialId;
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

    public LongFilter getArtistId() {
        return artistId;
    }

    public void setArtistId(LongFilter artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "BandCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (birthDate != null ? "birthDate=" + birthDate + ", " : "") +
                (bio != null ? "bio=" + bio + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (photo != null ? "photo=" + photo + ", " : "") +
                (napsterId != null ? "napsterId=" + napsterId + ", " : "") +
                (countryId != null ? "countryId=" + countryId + ", " : "") +
                (labelId != null ? "labelId=" + labelId + ", " : "") +
                (socialId != null ? "socialId=" + socialId + ", " : "") +
                (genreId != null ? "genreId=" + genreId + ", " : "") +
                (ratingId != null ? "ratingId=" + ratingId + ", " : "") +
                (favouriteId != null ? "favouriteId=" + favouriteId + ", " : "") +
                (artistId != null ? "artistId=" + artistId + ", " : "") +
            "}";
    }

}
