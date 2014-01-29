package com.dozortsev.adviceexchange.domain;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.GenerationTime.ALWAYS;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

    @Id @GeneratedValue(strategy = IDENTITY) @NotNull
    @Column(name = "id", unique = true, updatable = false)
    private ID id;

    @Version @Generated(ALWAYS)
    private Integer version;

    public ID getId() {
        return id;
    }
    @SuppressWarnings("unused")
    public void setId(ID id) {
        this.id = id;
    }

    /* todo: add version value / @date - 29 Jan 14 12:16 AM */

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
