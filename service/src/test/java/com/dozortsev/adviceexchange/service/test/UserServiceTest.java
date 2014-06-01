package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.*;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest extends TestContext {

    @Test public void testFindUserById() {

        // choose exist User Id
        final Long id = 1L;

        // expected data
        final String password = "2465010";
        final String login = "urna@Fuscealiquet.com";

        final User user = userService.findById(id);

        assertNotNull(user);
        assertEquals(login, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test public void testFindUserByLogin() {

        // choose User email(login)
        final String login = "urna@Fuscealiquet.com";

        // expected data
        final Long id = 1L;
        final String password = "2465010";

        final User user = userService.findByLogin(login);
        assertNotNull(user);

        assertEquals(id, user.getId());
        assertEquals(login, user.getEmail());
        assertEquals(password, user.getPassword());
        assertTrue(user.isEnabled());

        assertEquals(userService.findById(id), user);
    }

    @Test public void testCreateUser() {

        // prepare data
        final String name = "Carlos Castaneda";
        final int age = 50;
        final String aboutMe = "Programmer";
        final String location = "Mexico";
        final String site = "github.com/Castaneda";
        final String email = "castaneda@gmail.com";
        final String password = "helloCastaneda";
        final int reputation = 1000;
        final Badge badge = badgeService.findById(1L);    // Admin

        User user = new User(name, age, aboutMe, location, site, email, password, reputation);
        user.getBadges().add(badge);

        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        // reload
        final User expectUser = userService.findById(user.getId());

        assertTrue(expectUser.isEnabled());
        assertEquals(name, expectUser.getName());
        assertEquals(age, expectUser.getAge());
        assertEquals(aboutMe, expectUser.getAboutMe());
        assertEquals(location, expectUser.getLocation());
        assertEquals(site, expectUser.getSite());
        assertEquals(email, expectUser.getEmail());
        assertEquals(password, expectUser.getPassword());
        assertEquals(reputation, expectUser.getReputation());

        final Set<Badge> badges = badgeService.findByUserId(user.getId());

        assertEquals(1, badges.size());
        assertTrue(badges.contains(badge));
    }

    @Test public void testDisabledUser() {

        // choose exist User Id
        final String login = "non@nonjusto.edu";

        final User user = userService.findByLogin(login);
        assertNotNull(user);
        assertEquals(login, user.getEmail());

        // disable
        user.canEnabled(Boolean.FALSE);
        userService.update(user);

        assertNull(userService.findByLogin(login));
    }

    @Test public void testUpdateUser() {

        // choose exist User Id
        final User user = userService.findById(2L);

        final String oldEmail = user.getEmail();
        final int oldReputation = user.getReputation();

        user.setEmail("new_email@gmail.com");
        user.changeReputation(15);              // up on 15

        userService.update(user);

        // reload
        final User expectedUser = userService.findById(user.getId());

        assertNotEquals(oldEmail, expectedUser.getEmail());
        assertNotEquals(oldReputation, expectedUser.getReputation());
        assertEquals((oldReputation + 15), expectedUser.getReputation());
    }

    @Test public void testFindUserByName() {

        final String regex = "all";

        final Set<User> users = userService.findByName(regex, 0);
        assertEquals(3, users.size());
    }

    @Test public void testUserActivity() {

        // choose exist User id
        final Long id = 90L;

        // expected data
        final Answer answer = answerService.findById(30L);
        final Question question = questionService.findById(14L);

        Set<UserActivity> userActivities = userService.userActivities(id);

        assertEquals(2, userActivities.size());
        assertTrue(userActivities.contains(answer));
        assertTrue(userActivities.contains(question));
    }

    @Test public void testTotalCountByName() {
        Integer count = userService.totalCount("t");

        assertTrue(count.equals(48));
    }
}