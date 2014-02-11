package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<Long, Badge> implements BadgeDao {

    public BadgeDaoImpl() {
        setEntityClass(Badge.class);
    }
}
