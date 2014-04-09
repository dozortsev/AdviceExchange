package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class TagDaoImpl extends GenericDaoImpl<Long, Tag> implements TagDao {

    public TagDaoImpl() {
        setEntityClass(Tag.class);
    }

    @Override public Tag findTagByName(String name) {

        return (Tag) getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}
