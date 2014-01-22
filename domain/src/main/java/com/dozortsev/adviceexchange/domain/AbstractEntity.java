package com.dozortsev.adviceexchange.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class AbstractEntity<ID> implements Serializable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private ID id;

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
