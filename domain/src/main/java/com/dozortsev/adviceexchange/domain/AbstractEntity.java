package com.dozortsev.adviceexchange.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    @Id @GeneratedValue(strategy = IDENTITY) @NotNull
    @Column(name = "id", unique = true, updatable = false)
    protected ID id;

    public ID getId() {
        return id;
    }
    @SuppressWarnings("unused")
    public void setId(ID id) {
        this.id = id;
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override public int hashCode() {
        return id.hashCode();
    }
}
