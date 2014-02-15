package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest extends TestContext {

    @Test public void testFindUserById() {

        final Long id = 1L;

        final String expectPassword = "3938233";
        final String expectLogin = "dolor.Quisque.tincidunt@tellusnon.edu";

        User user = userService.findById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(expectLogin, user.getEmail());
        assertEquals(expectPassword, user.getPassword());
    }

    @Test public void testFindUserByLogin() {

        final String login = "dolor.Quisque.tincidunt@tellusnon.edu";

        final Long expectId = 1L;
        final String expectPassword = "3938233";

        User user = userService.findByLogin(login);

        assertNotNull(user);
        assertEquals(expectId, user.getId());
        assertEquals(login, user.getEmail());
        assertEquals(expectPassword, user.getPassword());
    }

    @Test public void testCreateUser() {

        // prepare User for test of create
        final Integer age = 25, reputation = 1;
        final String name = "Lukas Eder", aboutMe = "Java Object Oriented Querying",
                location = "Switzerland, Zurich", site = "github.com/lukaseder",
                email = "lukas.eder@gmail.com", password = "lukas_dev";

        User user = new User(name, age, aboutMe, location, site, email, password, reputation);

        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        user = userService.findById(user.getId());
        assertNotNull(user);

        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
        assertEquals(aboutMe, user.getAboutMe());
        assertEquals(location, user.getLocation());
        assertEquals(site, user.getSite());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(reputation, user.getReputation());
    }

    @Test public void testUpdateUser() {

        final Long id = 2L;

        User user = userService.findById(id);
        assertNotNull(user);

        final String changedEmail = "new_email@gmail.com";
        final Integer upReputation = 15, oldReputation = user.getReputation();

        user.setEmail(changedEmail);
        user.setReputation(upReputation);
        userService.update(user);
        
        assertEquals(changedEmail, user.getEmail());
        assertTrue(new Integer(upReputation + oldReputation).equals(user.getReputation()));
    }

    @Test public void testDelete() {

        final Long id = 3L;
        User user = userService.findById(id);

        assertNotNull(user);
        userService.delete(user);

        assertNull(userService.findById(id));
    }

    @Test public void testDeleteUserById() {

        final Long id = 4L;
        userService.deleteById(id);

        assertNull(userService.findById(id));
    }
}