package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<Long, Badge> implements BadgeDao {

    @Autowired
    private String findBadgesByUserId;

    public BadgeDaoImpl() {
        setEntityClass(Badge.class);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override public Set<Badge> findBadgesByUserId(Long userId) {
        Set<Badge> badges = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), userId));
            badges.addAll(
                    getCurrentSession().createSQLQuery(findBadgesByUserId)
                            .addEntity(getEntityClass()).setLong("userId", userId)
                            .list()
            );
            log.info(format("Set of %s have size: %s", getEntityClass(), badges.size()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return badges;
    }
}
