package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@SuppressWarnings("unchecked")
@Repository
public class GenericDaoImpl<ID extends Serializable, T extends AbstractEntity<ID>>
        extends AbstractDao<ID, T> implements GenericDao<ID, T> {

    private GenericDaoImpl() { }

    protected GenericDaoImpl(Class<T> entityClass) {
        super(entityClass);
    }

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

    @Override public void deleteById(ID id) {
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

    @Transactional(readOnly = true)
    @Override public T findById(ID id) {
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
            log.info(format("Updating %s. ID: %s", getEntityClass(), entity.getId()));
            getCurrentSession().update(entity);
            log.info("Successful updated");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity;
    }
}