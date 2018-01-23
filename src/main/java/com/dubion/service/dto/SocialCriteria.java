package com.dubion.service.dto;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

public class SocialCriteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter url;

    private BooleanFilter official;

    private StringFilter facebook;

    private  StringFilter twitter;

    private StringFilter youTube;

    private StringFilter googlePlus;

    private StringFilter instagram;

    public SocialCriteria() {
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

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public BooleanFilter getOfficial() {
        return official;
    }

    public void setOfficial(BooleanFilter official) {
        this.official = official;
    }

    public StringFilter getFacebook() {
        return facebook;
    }

    public void setFacebook(StringFilter facebook) {
        this.facebook = facebook;
    }

    public StringFilter getTwitter() {
        return twitter;
    }

    public void setTwitter(StringFilter twitter) {
        this.twitter = twitter;
    }

    public StringFilter getYouTube() {
        return youTube;
    }

    public void setYouTube(StringFilter youTube) {
        this.youTube = youTube;
    }

    public StringFilter getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(StringFilter googlePlus) {
        this.googlePlus = googlePlus;
    }

    public StringFilter getInstagram() {
        return instagram;
    }

    public void setInstagram(StringFilter instagram) {
        this.instagram = instagram;
    }

    @Override
    public String toString() {
        return "SocialCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (official != null ? "official=" + official + ", " : "") +
            (facebook != null ? "facebook=" + facebook + ", " : "") +
            (twitter != null ? "twitter=" + twitter + ", " : "") +
            (youTube != null ? "youTube=" + youTube + ", " : "") +
            (googlePlus != null ? "googlePlus=" + googlePlus + ", " : "") +
            (instagram != null ? "instagram=" + instagram + ", " : "") +
            '}';
    }
}
