package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;

import java.io.Serializable;

public interface GenericDao<ID extends Serializable, T extends AbstractEntity<ID>> {

    public ID create(T entity);

    public void delete(T entity);

    public void delete(ID id);

    public T findById(ID id);

    public T update(T entity);
}
