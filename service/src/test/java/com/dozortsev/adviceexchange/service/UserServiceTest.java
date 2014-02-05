package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;
import org.junit.Assert;

public class UserServiceTest extends TestContext {

    @Test public void testFindByLogin() {

        final String login = "sed.dui.Fusce@torquentperconubia.net";

        User user = userService.findByLogin(login);

        Assert.assertNotNull(user);
    }

    @Test public void testCreate() {

        // prepare data for test
        User user = new User(
                "Mario", 25, null, "Germany, Dortmund", null, "gotze@gmail.com", "gotze_mario", 1
        );
        Assert.assertNull(user.getId());
        userService.create(user);
        Assert.assertNotNull(user.getId());
    }
}