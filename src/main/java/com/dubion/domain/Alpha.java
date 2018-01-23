package com.dubion.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Alpha.
 */
@Entity
@Table(name = "alpha")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Alpha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "edad")
    private Integer edad;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "alpha_beta",
               joinColumns = @JoinColumn(name="alphas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="betas_id", referencedColumnName="id"))
    private Set<Beta> betas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public Alpha edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Set<Beta> getBetas() {
        return betas;
    }

    public Alpha betas(Set<Beta> betas) {
        this.betas = betas;
        return this;
    }

    public Alpha addBeta(Beta beta) {
        this.betas.add(beta);
        return this;
    }

    public Alpha removeBeta(Beta beta) {
        this.betas.remove(beta);
        return this;
    }

    public void setBetas(Set<Beta> betas) {
        this.betas = betas;
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
        Alpha alpha = (Alpha) o;
        if (alpha.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alpha.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Alpha{" +
            "id=" + getId() +
            ", edad='" + getEdad() + "'" +
            "}";
    }
}
