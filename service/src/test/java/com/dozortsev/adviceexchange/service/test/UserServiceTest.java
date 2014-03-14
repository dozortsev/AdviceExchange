package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
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

        final String expectPassword = "3938233";
        final String expectLogin = "dolor.Quisque.tincidunt@tellusnon.edu";

        User user = userService.findById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(expectLogin, user.getEmail());
        assertEquals(expectPassword, user.getPassword());
    }

    @Test public void testFindUserByLogin() {

        // choose User email(login)
        final String login = "dolor.Quisque.tincidunt@tellusnon.edu";

        final Long expectId = 1L;
        final String expectPassword = "3938233";

        final User user = userService.findUserByLogin(login);
        assertNotNull(user);

        assertEquals(expectId, user.getId());
        assertEquals(login, user.getEmail());
        assertEquals(expectPassword, user.getPassword());

        assertEquals(userService.findById(expectId), user);
    }

    @Test public void testCreateUserQuestion() {

        log.info("Create user with question");

        User user = new User(
                "Carlos Castaneda", 50, "Programmer", "Mexico", "github.com/Castaneda",
                "castaneda@gmail.com", "helloCastaneda", 1000
        );

        userService.create(user);
        assertNotNull(user.getId());

        Comment comment = new Comment(user, "jflj kjaslfkjs", questionService.findById(5L));

        /*Question question = new Question(
                user, "consectetur adipisicing elit. Aut blanditiis dolore eum ex explicabo",
                "dolore eum ex explicabo fuga harum", 100
        );*/

        // create Question
        /*commentService.create(comment);
        assertNotNull(comment.getId());*/

        user.getComments().add(comment);
        userService.update(user);

        Set<Comment> questionComments = commentService.findCommentsByQuestionId(5L);

        log.info("Create user with question - finish");
        assertTrue(questionComments.contains(comment));
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

        final Long id = 1L;

        Set<UserActivity> userActivities = userService.userActivities(id);

        assertNotEquals(0, userActivities.size());
    }
}