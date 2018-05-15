package com.dubion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Song.
 */
@Entity
@Table(name = "song")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "napster_id")
    private String napsterId;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "song_album",
               joinColumns = @JoinColumn(name="songs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="albums_id", referencedColumnName="id"))
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RatingSong> ratings = new HashSet<>();

    @OneToMany(mappedBy = "song")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FavouriteSong> favourites = new HashSet<>();

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

    public Song name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Song url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNapsterId() {
        return napsterId;
    }

    public Song napsterId(String napsterId) {
        this.napsterId = napsterId;
        return this;
    }

    public void setNapsterId(String napsterId) {
        this.napsterId = napsterId;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Song albums(Set<Album> albums) {
        this.albums = albums;
        return this;
    }

    public Song addAlbum(Album album) {
        this.albums.add(album);
        album.getSongs().add(this);
        return this;
    }

    public Song removeAlbum(Album album) {
        this.albums.remove(album);
        album.getSongs().remove(this);
        return this;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<RatingSong> getRatings() {
        return ratings;
    }

    public Song ratings(Set<RatingSong> ratingSongs) {
        this.ratings = ratingSongs;
        return this;
    }

    public Song addRating(RatingSong ratingSong) {
        this.ratings.add(ratingSong);
        ratingSong.setSong(this);
        return this;
    }

    public Song removeRating(RatingSong ratingSong) {
        this.ratings.remove(ratingSong);
        ratingSong.setSong(null);
        return this;
    }

    public void setRatings(Set<RatingSong> ratingSongs) {
        this.ratings = ratingSongs;
    }

    public Set<FavouriteSong> getFavourites() {
        return favourites;
    }

    public Song favourites(Set<FavouriteSong> favouriteSongs) {
        this.favourites = favouriteSongs;
        return this;
    }

    public Song addFavourite(FavouriteSong favouriteSong) {
        this.favourites.add(favouriteSong);
        favouriteSong.setSong(this);
        return this;
    }

    public Song removeFavourite(FavouriteSong favouriteSong) {
        this.favourites.remove(favouriteSong);
        favouriteSong.setSong(null);
        return this;
    }

    public void setFavourites(Set<FavouriteSong> favouriteSongs) {
        this.favourites = favouriteSongs;
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
        Song song = (Song) o;
        if (song.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), song.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Song{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", napsterId='" + getNapsterId() + "'" +
            "}";
    }
}
