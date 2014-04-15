package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.BadgeDao;
import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class BadgeServiceImpl extends GenericServiceImpl<Long, Badge> implements BadgeService {

    @Autowired private BadgeDao badgeDao;

    @Override public BadgeDao getDao() {
        return badgeDao;
    }

    public BadgeServiceImpl() {
        setEntityClass(Badge.class);
    }

    @Transactional(readOnly = true)
    @Override public Set<Badge> findBadgesByUserId(Long userId) {
        Set<Badge> badges = new HashSet<>();
        try {
            log.info(format("Find Badges by User Id: %d", userId));
            badges.addAll(getDao().findBadgesByUserId(userId));
            log.info(format("Set of Badges have size: %d", badges.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return badges;
    }
}
