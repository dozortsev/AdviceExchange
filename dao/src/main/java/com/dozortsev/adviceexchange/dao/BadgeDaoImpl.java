package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<Long, Badge> implements BadgeDao {

    @Autowired
    private String findBadgesByUserId;

    public BadgeDaoImpl() {
        setEntityClass(Badge.class);
    }

    @SuppressWarnings("unchecked")
    @Override public List<Badge> findByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findBadgesByUserId)
                .addEntity(getEntityClass()).setLong("userId", userId)
                .list();
    }
}
