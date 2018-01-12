package com.dubion.service.dto;

import io.github.jhipster.service.filter.*;

import java.io.Serializable;



public class SongCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private DoubleFilter duration;

    public SongCriteria() {
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

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public DoubleFilter getDuration() {
        return duration;
    }

    public void setDuration(DoubleFilter duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SongCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (duration != null ? "duration=" + duration + ", " : "") +
            '}';
    }
}
