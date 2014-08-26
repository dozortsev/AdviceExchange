package com.dozortsev.adviceexchange.web.util;

import com.dozortsev.adviceexchange.domain.User;

public class TagUtils {

    static boolean isAdmin(User user) {
        return user.getBadges()
                .stream()
                .map(bdg -> bdg.getName().equals("ROLE_ADMIN"))
                .findFirst()
                .orElse(false);
    }
}
