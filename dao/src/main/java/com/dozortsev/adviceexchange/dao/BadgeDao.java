package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;

import java.util.Set;

public interface BadgeDao extends GenericDao<Long, Badge> {

    Set<Badge> findBadgesByUserId(Long userId);
}
