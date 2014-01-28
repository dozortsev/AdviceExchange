package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static java.lang.String.format;

@SuppressWarnings("unchecked")
@Repository
public class GenericDaoImpl<ID extends Serializable, T extends AbstractEntity<ID>>
        extends AbstractDao<ID, T> implements GenericDao<ID, T> {

    private GenericDaoImpl() { }

    protected GenericDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

    /*  todo: create question about log convention on crud method / @date - 29 Jan 14 12:22 AM */

    @Override
    public ID create(T entity) {
        try {
            log.info(format("Create new %s", getEntityClass()));
            getCurrentSession().save(entity);
            log.info(format("Successful created"));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity.getId();
    }

    @Override
    public void delete(T entity) {
        try {
            getCurrentSession().delete(entity);

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public void delete(ID id) {
        try {
            T entity = (T) getCurrentSession().get(getEntityClass(), id);

            getCurrentSession().delete(entity);

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public T get(ID id) {
        try {
            return (T) getCurrentSession().get(getEntityClass(), id);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override
    public T update(T entity) {
        try {
            getCurrentSession().update(entity);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity;
    }
}
