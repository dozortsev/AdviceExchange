package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.dao.UserDao;
import com.dozortsev.adviceexchange.domain.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/application-context-test.xml")
public class UserServiceTest {

    static final Logger log = Logger.getLogger(TestContext.class);

    @Autowired(required = false)
    public UserDao userDao;

    @Autowired
    public String line;

    private static EmbeddedDatabase db;

    /*@BeforeClass public static void setUp() {
        log.info("creates an HSQL in-memory database");
        db = new EmbeddedDatabaseBuilder().setName("embeddedDatabase").build();
    }

    @AfterClass public static void tearDown() {
        log.info("shutdown database");
        db.shutdown();
    }*/

    @Test
    public void testHello() {

        Assert.assertNull(line);
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