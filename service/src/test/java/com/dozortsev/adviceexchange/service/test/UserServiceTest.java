package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Badge;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest extends TestContext {

    @Test public void testFindUserById() {

        // choose random User Id
        final Long id = 1L;

        final String expectPassword = "2465010";
        final String expectLogin = "urna@Fuscealiquet.com";

        User user = userService.findById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(expectLogin, user.getEmail());
        assertEquals(expectPassword, user.getPassword());
    }

    @Test public void testFindUserByLogin() {

        // choose User email(login)
        final String login = "urna@Fuscealiquet.com";

        final Long expectId = 1L;
        final String expectPassword = "2465010";

        final User user = userService.findUserByLogin(login);
        assertNotNull(user);

        assertEquals(expectId, user.getId());
        assertEquals(login, user.getEmail());
        assertEquals(expectPassword, user.getPassword());

        assertEquals(userService.findById(expectId), user);
    }

    @Test public void testCreateUser() {

        // prepare data
        final String expectName = "Carlos Castaneda";
        final Integer expectAge = 50;
        final String expectAboutMe = "Programmer";
        final String expectLocation = "Mexico";
        final String expectSite = "github.com/Castaneda";
        final String expectEmail = "castaneda@gmail.com";
        final String expectPassword = "helloCastaneda";
        final Integer expectReputation = 1000;
        final Badge expectBadge = badgeService.findById(1L);    // Admin

        User user = new User(
                expectName, expectAge, expectAboutMe, expectLocation,
                expectSite, expectEmail, expectPassword, expectReputation
        );
        user.getBadges().add(expectBadge);

        // create
        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        // reload
        final User expectUser = userService.findById(user.getId());

        assertEquals(expectName, expectUser.getName());
        assertEquals(expectAge, expectUser.getAge());
        assertEquals(expectAboutMe, expectUser.getAboutMe());
        assertEquals(expectLocation, expectUser.getLocation());
        assertEquals(expectSite, expectUser.getSite());
        assertEquals(expectEmail, expectUser.getEmail());
        assertEquals(expectPassword, expectUser.getPassword());
        assertEquals(expectReputation, expectUser.getReputation());
        assertEquals(1, expectUser.getBadges().size());
        assertTrue(expectUser.getBadges().contains(expectBadge));
    }

    @Test public void testUpdateUser() {

        // choose random User Id
        final Long userId = 2L;

        final User user = userService.findById(userId);
        assertNotNull(user);

        final String changedEmail = "new_email@gmail.com";
        final Integer upReputation = 15, oldReputation = user.getReputation();
        final Date joined = user.getJoined();

        // set updates
        user.setEmail(changedEmail);
        user.setReputation(upReputation);

        // persist changes
        userService.update(user);

        // reload
        final User expectedUser = userService.findById(userId);

        assertEquals(expectedUser.getJoined(), joined);
        assertEquals(expectedUser.getEmail(), changedEmail);
        assertTrue(new Integer(upReputation + oldReputation).equals(expectedUser.getReputation()));
    }

    @Test public void testUserActivity() {

        final Long id = 2L;

        Set<UserActivity> userActivities = userService.userActivities(id);

        assertNotEquals(0, userActivities.size());
    }
}