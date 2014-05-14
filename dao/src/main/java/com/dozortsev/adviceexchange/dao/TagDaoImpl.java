package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@SuppressWarnings("unchecked")
@Repository
public class TagDaoImpl extends GenericDaoImpl<Long, Tag> implements TagDao {

    public TagDaoImpl() {
        setEntityClass(Tag.class);
    }

    @Override public List<Tag> loadAll() {
        return getCurrentSession().createCriteria(getEntityClass())
                .list();
    }

    @Override public List<Tag> findByName(String... names) {

        return getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.in("name", names)).list();
    }
}
