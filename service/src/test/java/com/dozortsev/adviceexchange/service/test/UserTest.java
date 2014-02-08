package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UserTest extends TestContext {

    @Test
    public void testHello() {

        Assert.assertNotNull(line);
        Assert.assertEquals("Hello", line);
    }

    @Ignore public void testFindByLogin() {

        final String login = "sed.dui.Fusce@torquentperconubia.net";

        Assert.assertNotNull(userDao);
        User user = userDao.findByLogin(login);

        Assert.assertNotNull("User must be not null", user);
    }

    @Ignore public void testCreate() {

        // prepare data for service
        User user = new User(
                "Mario", 25, null, "Germany, Dortmund", null, "gotze@gmail.com", "gotze_mario", 1
        );
        Assert.assertNull(user.getId());
        userDao.create(user);
        Assert.assertNotNull(user.getId());
    }
}