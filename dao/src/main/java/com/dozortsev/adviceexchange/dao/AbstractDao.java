package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractDao<ID extends Serializable, T extends AbstractEntity<ID>> {

    private Class<T> entityClass;

    public final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private SessionFactory factory;

    protected AbstractDao() { }

    protected AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}