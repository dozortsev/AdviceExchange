package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.service.UserService;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/test-application-context.xml")
public abstract class TestContext {

    static final Logger log = Logger.getLogger(TestContext.class);

    @Autowired
    public UserService userService;

    private static EmbeddedDatabase db;

    @BeforeClass
    public static void setUp() {
        log.info("creates an HSQL in-memory database");
        db = new EmbeddedDatabaseBuilder().setName("embeddedDatabase").build();
    }

    @AfterClass
    public static void tearDown() {
        log.info("shutdown database");
        db.shutdown();
    }
}