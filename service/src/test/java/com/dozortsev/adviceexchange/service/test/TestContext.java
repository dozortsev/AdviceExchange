package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.dao.UserDao;
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
@ContextConfiguration(locations = "classpath*:/spring/application-context-test.xml")
public abstract class TestContext {

    /* TODO: add application Context in Web layer http://goo.gl/bniSAJ */
    
    /* TODO: read topic http://goo.gl/twxwma about cause bean no injected */

    /* TODO: create test app. context xml file */

    static final Logger log = Logger.getLogger(TestContext.class);

    @Autowired(required = false)
    public UserDao userDao;

    @Autowired
    public String line;

    private static EmbeddedDatabase db;

    @BeforeClass public static void setUp() {
        log.info("creates an HSQL in-memory database");
        db = new EmbeddedDatabaseBuilder().setName("embeddedDatabase").build();
    }

    @AfterClass public static void tearDown() {
        log.info("shutdown database");
        db.shutdown();
    }
}