package com.dubion.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;


    /**
     * Criteria class for the Band entity. This class is used in BandResource to
     * receive all the possible filtering options from the Http GET request parameters.
     * For example the following could be a valid requests:
     * <code> /band-pruebas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
     * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
     * fix type specific filters.
     */
    public class FavouriteBandCriteria implements Serializable {
        private static final long serialVersionUID = 1L;


        private Boolean liked;

        private LocalDateFilter date;

        public FavouriteBandCriteria() {
        }

        public Boolean getId() {
            return liked;
        }

        public void setId(Boolean id) {
            this.liked = id;
        }

        public LocalDateFilter getName() {
            return date;
        }

        public void setName(LocalDateFilter name) {
            this.date = name;
        }

        @Override
        public String toString() {
            return "FavouriteBandCriteria{" +
                (liked != null ? "id=" + liked + ", " : "") +
                (date != null ? "name=" + date + ", " : "") +
                "}";
        }


}
