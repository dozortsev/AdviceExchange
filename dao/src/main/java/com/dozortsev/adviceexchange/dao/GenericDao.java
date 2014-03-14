package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;

import java.io.Serializable;

public interface GenericDao<ID extends Serializable, T extends AbstractEntity<ID>> {

    ID create(T entity);

    @Deprecated
    void delete(T entity);

    @Deprecated
    void deleteById(ID id);

    T findById(ID id);

    T update(T entity);
}
