package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TBadge;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Badge;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.BadgeRecord;

import java.util.List;

public interface BadgeDao extends GenericDao<BadgeRecord, TBadge> {

    List<Badge> findByUserId(int userId);
}
