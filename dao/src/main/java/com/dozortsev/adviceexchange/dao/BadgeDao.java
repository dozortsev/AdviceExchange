package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Badge;

import java.util.List;

public interface BadgeDao extends GenericDao<Long, Badge> {

    List<Badge> findByUserId(Long userId);
}
