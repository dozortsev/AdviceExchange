package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Badge;

import java.util.Set;

public interface BadgeService extends GenericService<Long, Badge> {

    Set<Badge> findByUserId(long userId);
}
