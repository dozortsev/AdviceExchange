package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.GenericDao;
import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, rollbackFor = Exception.class)
@Service
public abstract class GenericServiceImpl<ID extends Serializable, T extends AbstractEntity<ID>> implements GenericService<ID, T> {

    private Class<T> entityClass;

    final Logger log = Logger.getLogger(this.getClass().getName());

    protected abstract GenericDao<ID, T> getDao();

    @Override public ID create(T entity) {
        try {
            log.info(format("Create new %s", getEntityClass().getSimpleName()));
            getDao().create(entity);
            log.info(format("Success create; ID: %s", entity.getId()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity.getId();
    }

    @Override public void delete(T entity) {
        try {
            log.info(format("Delete %s", getEntityClass().getSimpleName()));
            getDao().delete(entity);
            log.info("Success delete");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override public void deleteById(ID id) {
        try {
            log.info(format("Delete %s by ID: %s", getEntityClass().getSimpleName(), id));
            getDao().deleteById(id);
            log.info("Success delete");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Transactional(readOnly = true)
    @Override public T findById(ID id) {
        try {
            log.info(format("Find %s by ID: %s", getEntityClass().getSimpleName(), id));
            T entity = getDao().findById(id);

            if (entity != null) {
                log.info("Success found");
                return entity;
            }
            log.info(format("%s with this ID not exist", getEntityClass().getSimpleName()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override public T update(T entity) {
        try {
            log.info(format("Update %s. ID: %s", getEntityClass().getSimpleName(), entity.getId()));
            getDao().update(entity);
            log.info("Success updated");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return entity;
    }

    @Override public Integer totalCount() {
        int totalCount = 0;
        try {
            totalCount = getDao().totalCount();
            log.info(format("Total count of %ss is: %d", getEntityClass().getSimpleName(), totalCount));
            return totalCount;

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return totalCount;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
