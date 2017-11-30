package com.dubion.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Social.
 */
@Entity
@Table(name = "social")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Social implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "official")
    private Boolean official;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "you_tube")
    private String youTube;

    @Column(name = "google_plus")
    private String googlePlus;

    @Column(name = "instagram")
    private String instagram;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Social url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isOfficial() {
        return official;
    }

    public Social official(Boolean official) {
        this.official = official;
        return this;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public String getFacebook() {
        return facebook;
    }

    public Social facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Social twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYouTube() {
        return youTube;
    }

    public Social youTube(String youTube) {
        this.youTube = youTube;
        return this;
    }

    public void setYouTube(String youTube) {
        this.youTube = youTube;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public Social googlePlus(String googlePlus) {
        this.googlePlus = googlePlus;
        return this;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getInstagram() {
        return instagram;
    }

    public Social instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Social social = (Social) o;
        if (social.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), social.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Social{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", official='" + isOfficial() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", youTube='" + getYouTube() + "'" +
            ", googlePlus='" + getGooglePlus() + "'" +
            ", instagram='" + getInstagram() + "'" +
            "}";
    }
}
