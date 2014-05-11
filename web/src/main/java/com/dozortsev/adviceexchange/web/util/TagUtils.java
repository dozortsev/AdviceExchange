package com.dozortsev.adviceexchange.web.util;

import com.dozortsev.adviceexchange.domain.Badge;
import com.dozortsev.adviceexchange.domain.User;

public class TagUtils {

    static boolean isAdmin(User user) {
        for (Badge badge : user.getBadges()) {
            if (badge.getName().equals("ROLE_ADMIN"))
                return true;
        }
        return false;
    }
}
