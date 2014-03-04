package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Badge;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BadgeServiceTest extends TestContext {

    @Test public void testUserBadges() {

        // choose random Badge Id
        final Long userId = 23L;

        final Set<Badge> userBadges = badgeService.findBadgesByUserId(userId);

        assertNotNull(userBadges);
        assertNotEquals(0, userBadges.size());

        for (Badge badge : userBadges) {

            Badge expectBadge = badgeService.findById(badge.getId());

            assertEquals(expectBadge, badge);
        }
    }
}
