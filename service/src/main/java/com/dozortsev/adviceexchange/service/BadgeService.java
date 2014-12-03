package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Badge;

import java.util.Set;

public interface BadgeService extends GenericService<Badge> {

    Set<Badge> findByUserId(int userId);
}
