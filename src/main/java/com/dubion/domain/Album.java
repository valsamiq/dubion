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

/**
 * A Album.
 */
@Entity
@Table(name = "album")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "photo")
    private String photo;

    @Column(name = "napster_id")
    private String napsterId;

    @ManyToOne
    private Band band;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "album_genre",
               joinColumns = @JoinColumn(name="albums_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="genres_id", referencedColumnName="id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RatingAlbum> ratings = new HashSet<>();

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FavouriteAlbum> favourites = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Song> songs = new HashSet<>();

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

    public Album name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Album releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPhoto() {
        return photo;
    }

    public Album photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNapsterId() {
        return napsterId;
    }

    public Album napsterId(String napsterId) {
        this.napsterId = napsterId;
        return this;
    }

    public void setNapsterId(String napsterId) {
        this.napsterId = napsterId;
    }

    public Band getBand() {
        return band;
    }

    public Album band(Band band) {
        this.band = band;
        return this;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Album genres(Set<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Album addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getAlbums().add(this);
        return this;
    }

    public Album removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getAlbums().remove(this);
        return this;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<RatingAlbum> getRatings() {
        return ratings;
    }

    public Album ratings(Set<RatingAlbum> ratingAlbums) {
        this.ratings = ratingAlbums;
        return this;
    }

    public Album addRating(RatingAlbum ratingAlbum) {
        this.ratings.add(ratingAlbum);
        ratingAlbum.setAlbum(this);
        return this;
    }

    public Album removeRating(RatingAlbum ratingAlbum) {
        this.ratings.remove(ratingAlbum);
        ratingAlbum.setAlbum(null);
        return this;
    }

    public void setRatings(Set<RatingAlbum> ratingAlbums) {
        this.ratings = ratingAlbums;
    }

    public Set<FavouriteAlbum> getFavourites() {
        return favourites;
    }

    public Album favourites(Set<FavouriteAlbum> favouriteAlbums) {
        this.favourites = favouriteAlbums;
        return this;
    }

    public Album addFavourite(FavouriteAlbum favouriteAlbum) {
        this.favourites.add(favouriteAlbum);
        favouriteAlbum.setAlbum(this);
        return this;
    }

    public Album removeFavourite(FavouriteAlbum favouriteAlbum) {
        this.favourites.remove(favouriteAlbum);
        favouriteAlbum.setAlbum(null);
        return this;
    }

    public void setFavourites(Set<FavouriteAlbum> favouriteAlbums) {
        this.favourites = favouriteAlbums;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public Album songs(Set<Song> songs) {
        this.songs = songs;
        return this;
    }

    public Album addSong(Song song) {
        this.songs.add(song);
        song.getAlbums().add(this);
        return this;
    }

    public Album removeSong(Song song) {
        this.songs.remove(song);
        song.getAlbums().remove(this);
        return this;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
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
        Album album = (Album) o;
        if (album.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), album.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Album{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", napsterId='" + getNapsterId() + "'" +
            "}";
    }
}
