package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;

import java.io.Serializable;

public interface GenericDao<ID extends Serializable, T extends AbstractEntity<ID>> {

    ID create(T entity);

    void delete(T entity);

    void deleteById(ID id);

    T findById(ID id);

    T update(T entity);

    Integer totalCount();
}
