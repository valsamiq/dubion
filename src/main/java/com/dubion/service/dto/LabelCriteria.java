package com.dubion.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;

/**
 * Criteria class for the Label entity. This class is used in LabelResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /labels?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */

public class LabelCriteria implements Serializable{
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    public LabelCriteria() {
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


    @Override
    public String toString() {
        return "LabelCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            "}";
    }
}
