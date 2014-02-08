package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.stereotype.Repository;

@Repository
public class BadgeDaoImpl extends GenericDaoImpl<Long, Badge> implements BadgeDao {

    protected BadgeDaoImpl() {
        this.entityClass = Badge.class;
    }
}
