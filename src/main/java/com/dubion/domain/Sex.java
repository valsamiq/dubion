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
 * A Sex.
 */
@Entity
@Table(name = "sex")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orientation")
    private String orientation;

    @OneToMany(mappedBy = "sex")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserExt> users = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public Sex orientation(String orientation) {
        this.orientation = orientation;
        return this;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Set<UserExt> getUsers() {
        return users;
    }

    public Sex users(Set<UserExt> userExts) {
        this.users = userExts;
        return this;
    }

    public Sex addUser(UserExt userExt) {
        this.users.add(userExt);
        userExt.setSex(this);
        return this;
    }

    public Sex removeUser(UserExt userExt) {
        this.users.remove(userExt);
        userExt.setSex(null);
        return this;
    }

    public void setUsers(Set<UserExt> userExts) {
        this.users = userExts;
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
        Sex sex = (Sex) o;
        if (sex.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sex{" +
            "id=" + getId() +
            ", orientation='" + getOrientation() + "'" +
            "}";
    }
}
