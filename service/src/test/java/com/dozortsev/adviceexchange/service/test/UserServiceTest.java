package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Badge;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.junit.Test;

import java.util.Set;
import java.util.regex.Pattern;

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

        final User user = userService.findUserByLogin(login);
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
        final Integer age = 50;
        final String aboutMe = "Programmer";
        final String location = "Mexico";
        final String site = "github.com/Castaneda";
        final String email = "castaneda@gmail.com";
        final String password = "helloCastaneda";
        final Integer reputation = 1000;
        final Badge badge = badgeService.findById(1L);    // Admin

        User user = new User(name, age, aboutMe, location, site, email, password, reputation);
        user.getBadges().add(badge);

        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        // reload
        final User expectUser = userService.findById(user.getId());

        assertEquals(name, expectUser.getName());
        assertEquals(age, expectUser.getAge());
        assertEquals(aboutMe, expectUser.getAboutMe());
        assertEquals(location, expectUser.getLocation());
        assertEquals(site, expectUser.getSite());
        assertEquals(email, expectUser.getEmail());
        assertEquals(password, expectUser.getPassword());
        assertTrue(expectUser.getReputation().equals(reputation + 1));

        final Set<Badge> badges = badgeService.findBadgesByUserId(user.getId());

        assertEquals(1, badges.size());
        assertTrue(badges.contains(badge));
    }

    @Test public void testDisabledUser() {

        // choose exist User Id
        final String login = "non@nonjusto.edu";

        final User user = userService.findUserByLogin(login);
        assertNotNull(user);
        assertEquals(login, user.getEmail());

        // disable
        user.wasEnabled(Boolean.FALSE);
        userService.update(user);

        assertNull(userService.findUserByLogin(login));
    }

    @Test public void testUpdateUser() {

        // choose exist User Id
        final User user = userService.findById(2L);

        final String oldEmail = user.getEmail();
        final Integer oldReputation = user.getReputation();

        user.setEmail("new_email@gmail.com");
        user.setReputation(15);

        userService.update(user);

        // reload
        final User expectedUser = userService.findById(user.getId());

        assertNotEquals(oldEmail, expectedUser.getEmail());
        assertNotEquals(oldReputation, expectedUser.getReputation());
        assertTrue(expectedUser.getReputation().equals(oldReputation + 15));
    }

    @Test public void testFindUserByName() {

        final String regexUserName = "all";

        for (User user : userService.findUsersByName(regexUserName)) {
            assertTrue(Pattern.compile(regexUserName).matcher(user.getName()).find());
        }
    }

//    @Ignore
    @Test public void testUserActivity() {

        final Long id = 2L;

        // expected Answer
        final Answer answer = answerService.findById(44L);

        Set<UserActivity> userActivities = userService.userActivities(id);

        assertEquals(1, userActivities.size());
        assertTrue(userActivities.contains(answer));
    }
}