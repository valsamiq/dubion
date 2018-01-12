package com.dubion.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;

/**
 * Criteria class for the Album entity. This class is used in AlbumResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /albums?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */

public class AlbumCriteria implements Serializable{
    private static final long serialVersionUID = 1L;

    private LongFilter id;
    private StringFilter name;
    private LocalDateFilter releaseDate;

    public AlbumCriteria() {
    }

    public LongFilter getId(){
        return id;
    }

    public void setId(LongFilter id){
        this.id = id;
    }

    public StringFilter getName(){
        return name;
    }

    public void setName(StringFilter name){
        this.name=name;
    }

    public LocalDateFilter getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateFilter releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "AlbumCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") +
    "}";
    }
}
