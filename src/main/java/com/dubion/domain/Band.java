package com.dubion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.dubion.domain.enumeration.Status;

/**
 * A Band.
 */
@Entity
@Table(name = "band")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "bio")
    private String bio;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Label label;

    @ManyToOne
    private Social social;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "band_genre",
               joinColumns = @JoinColumn(name="bands_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="genres_id", referencedColumnName="id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "band")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RatingBand> ratings = new HashSet<>();

    @OneToMany(mappedBy = "band")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FavouriteBand> favourites = new HashSet<>();

    @ManyToMany(mappedBy = "bands")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Artist> artists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Band name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Band birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public Band bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Band photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Band photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public Status getStatus() {
        return status;
    }

    public Band status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Country getCountry() {
        return country;
    }

    public Band country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Label getLabel() {
        return label;
    }

    public Band label(Label label) {
        this.label = label;
        return this;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Social getSocial() {
        return social;
    }

    public Band social(Social social) {
        this.social = social;
        return this;
    }

    public void setSocial(Social social) {
        this.social = social;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Band genres(Set<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Band addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getBands().add(this);
        return this;
    }

    public Band removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getBands().remove(this);
        return this;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<RatingBand> getRatings() {
        return ratings;
    }

    public Band ratings(Set<RatingBand> ratingBands) {
        this.ratings = ratingBands;
        return this;
    }

    public Band addRating(RatingBand ratingBand) {
        this.ratings.add(ratingBand);
        ratingBand.setBand(this);
        return this;
    }

    public Band removeRating(RatingBand ratingBand) {
        this.ratings.remove(ratingBand);
        ratingBand.setBand(null);
        return this;
    }

    public void setRatings(Set<RatingBand> ratingBands) {
        this.ratings = ratingBands;
    }

    public Set<FavouriteBand> getFavourites() {
        return favourites;
    }

    public Band favourites(Set<FavouriteBand> favouriteBands) {
        this.favourites = favouriteBands;
        return this;
    }

    public Band addFavourite(FavouriteBand favouriteBand) {
        this.favourites.add(favouriteBand);
        favouriteBand.setBand(this);
        return this;
    }

    public Band removeFavourite(FavouriteBand favouriteBand) {
        this.favourites.remove(favouriteBand);
        favouriteBand.setBand(null);
        return this;
    }

    public void setFavourites(Set<FavouriteBand> favouriteBands) {
        this.favourites = favouriteBands;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public Band artists(Set<Artist> artists) {
        this.artists = artists;
        return this;
    }

    public Band addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getBands().add(this);
        return this;
    }

    public Band removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getBands().remove(this);
        return this;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
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
        Band band = (Band) o;
        if (band.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), band.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Band{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", bio='" + getBio() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
