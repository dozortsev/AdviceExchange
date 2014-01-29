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

    @Override public ID create(T entity) {
        try {
            log.info(format("Create new %s", getEntityClass()));
            getCurrentSession().save(entity);
            log.info(format("Successful created. ID: %s", entity.getId()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity.getId();
    }

    @Override public void delete(T entity) {
        try {
            log.info(format("Deleting %s", getEntityClass()));
            getCurrentSession().delete(entity);
            log.info("Successful deleted");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override public void delete(ID id) {
        try {
            log.info(format("Deleting %s by Id: %s", getEntityClass(), id));
            T entity = (T) getCurrentSession().get(getEntityClass(), id);

            if (entity != null) {
                getCurrentSession().delete(entity);
                log.info("Successful deleted");
                return;
            }
            log.info(format("%s with this ID not exist", getEntityClass()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override public T get(ID id) {
        try {
            log.info(format("Finding %s by Id: %s", getEntityClass(), id));
            T entity = (T) getCurrentSession().get(getEntityClass(), id);

            if (entity != null) {
                log.info("Successful found");
                return entity;
            }
            log.info("%s with this ID not exist");
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override public T update(T entity) {
        try {
            log.info("Updating %s. ID: %s", getEntityClass(), entity.getId());
            getCurrentSession().update(entity);
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity;
    }
}
