package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<ID extends Serializable, T extends AbstractEntity<ID>> implements GenericDao<ID, T> {

    private Class<T> entityClass;

    private @Autowired SessionFactory factory;

    @Override public ID create(T entity) {
        getCurrentSession().save(entity);

        return entity.getId();
    }

    @Override public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override public void deleteById(ID id) {
        T entity = (T) getCurrentSession().get(getEntityClass(), id);

        getCurrentSession().delete(entity);
    }

    @Transactional(readOnly = true)
    @Override public T findById(ID id) {
        return (T) getCurrentSession().get(getEntityClass(), id);
    }

    @Override public T update(T entity) {
        getCurrentSession().update(entity);

        return entity;
    }

    @Transactional(readOnly = true)
    @Override public int totalCount() {
        return getCurrentSession().createCriteria(getEntityClass())
                .setProjection(Projections.rowCount())
                .uniqueResult().hashCode();
    }

    Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    Class<T> getEntityClass() {
        return entityClass;
    }
    void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}