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
 * Criteria class for the Alpha entity. This class is used in AlphaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /alphas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AlphaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter edad;

    private LongFilter betaId;

    public StringFilter getBetaName() {
        return betaName;
    }

    public void setBetaName(StringFilter betaName) {
        this.betaName = betaName;
    }

    private StringFilter betaName;

    public AlphaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getEdad() {
        return edad;
    }

    public void setEdad(IntegerFilter edad) {
        this.edad = edad;
    }

    public LongFilter getBetaId() {
        return betaId;
    }

    public void setBetaId(LongFilter betaId) {
        this.betaId = betaId;
    }

    @Override
    public String toString() {
        return "AlphaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (edad != null ? "edad=" + edad + ", " : "") +
                (betaId != null ? "betaId=" + betaId + ", " : "") +
            "}";
    }

}
