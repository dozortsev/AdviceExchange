package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<Long, Badge> implements BadgeDao {

    private String findBadgesByUserId;

    @Autowired
    @Override public void setQuery(String findBadgesByUserId) {
        this.findBadgesByUserId = findBadgesByUserId;
    }

    @Override public String getQuery() {
        return findBadgesByUserId;
    }

    public BadgeDaoImpl() {
        setEntityClass(Badge.class);
    }
}
