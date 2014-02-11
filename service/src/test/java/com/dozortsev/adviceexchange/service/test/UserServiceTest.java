package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UserServiceTest extends TestContext {

    @Ignore public void testFindByLogin() {

        final String login = "sed.dui.Fusce@torquentperconubia.net";

        Assert.assertNotNull(userService);
        User user = userService.findByLogin(login);

        Assert.assertNotNull(user);
    }

    @Test public void testCreate() {

        // prepare data for service
        User user = new User(
                "Mario", 25, null, "Germany, Dortmund", null, "gotze@gmail.com", "gotze_mario", 1
        );
        Assert.assertNull(user.getId());
        userService.create(user);
        Assert.assertNotNull(user.getId());
    }
}