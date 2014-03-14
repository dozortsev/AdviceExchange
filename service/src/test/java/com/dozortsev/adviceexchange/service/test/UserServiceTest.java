package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.*;
import org.junit.Ignore;
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

        // choose random User email(login)
        final String login = "dolor.Quisque.tincidunt@tellusnon.edu";

        final Long expectId = 1L;
        final String expectPassword = "3938233";

        User user = userService.findUserByLogin(login);

        assertNotNull(user);
        assertEquals(expectId, user.getId());
        assertEquals(login, user.getEmail());
        assertEquals(expectPassword, user.getPassword());
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

    @Ignore
    @Test public void testCreateUser() {

        // prepare User for test of create
        final Integer age = 25, reputation = 1;
        final String name = "Lukas Eder", aboutMe = "Java Object Oriented Querying",
                location = "Switzerland, Zurich", site = "github.com/lukaseder",
                email = "lukas.eder@gmail.com", password = "lukas_dev";

        User user = new User(name, age, aboutMe, location, site, email, password, reputation);

        final Badge badge = badgeService.findById(1L);
        user.getBadges().add(badge);

        // new User asked 1 question
        final Question question = new Question(user,
                "consectetur adipisicing elit. Aut blanditiis dolore eum ex explicabo",
                "dolore eum ex explicabo fuga harum", 1
        );
        // add to Question 1 Tag
        question.getTags().add(tagService.findById(4L));

        // Question have 1 accepted Answer
        final Answer answer = new Answer(
                userService.findById(50L), "consectetur adipisicing elit Content Aut blanditiis dolore eum ex explicabo",
                question, 10, true
        );
        question.getAnswers().add(answer);

        // set this Question to User
//        user.getQuestions().add(question);

        // try to create new User
        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        questionService.create(question);

        // reload
        User expectUser = userService.findById(user.getId());
        assertNotNull(user);

        // check on the expected data
        assertEquals(name, expectUser.getName());
        assertEquals(age, expectUser.getAge());
        assertEquals(aboutMe, expectUser.getAboutMe());
        assertEquals(location, expectUser.getLocation());
        assertEquals(site, expectUser.getSite());
        assertEquals(email, expectUser.getEmail());
        assertEquals(password, expectUser.getPassword());
        assertEquals(reputation, expectUser.getReputation());

        Set<Badge> userBadges = badgeService.findBadgesByUserId(expectUser.getId());
        assertEquals(1, userBadges.size());
        assertTrue(userBadges.contains(badge));

        Set<Question> userQuestions = questionService.findQuestionsByUserId(expectUser.getId());
        assertEquals(1, userQuestions.size());
        assertTrue(userQuestions.contains(question));

        Set<Answer> questionAnswers = answerService.findAnswersByQuestionId(question.getId());
        assertEquals(1, questionAnswers.size());
        assertTrue(questionAnswers.contains(answer));
    }

    @Test public void testUpdateUser() {

        // choose random User Id
        final Long id = 2L;

        User user = userService.findById(id);
        assertNotNull(user);

        final String changedEmail = "new_email@gmail.com";
        final Integer upReputation = 15, oldReputation = user.getReputation();
        final Date joined = user.getJoined();

        // set updates
        user.setEmail(changedEmail);
        user.setReputation(upReputation);
        userService.update(user);

        assertEquals(joined, user.getJoined());
        assertEquals(changedEmail, user.getEmail());
        assertEquals(Integer.valueOf(upReputation + oldReputation), user.getReputation());
    }

    @Ignore
    @Test public void testDelete() {

        // choose random User Id
        final Long id = 5L;
        User user = userService.findById(id);

        assertNotNull(user);
        userService.delete(user);

        assertNull(userService.findById(id));

        // With User should be delete all him Questions
        assertTrue(questionService.findQuestionsByUserId(id).isEmpty());
        // Answers
        assertTrue(answerService.findAnswersByUserId(id).isEmpty());
        // also Badges
        assertTrue(badgeService.findBadgesByUserId(id).isEmpty());
    }

    @Ignore
    @Test public void testDeleteUserById() {

        final Long id = 4L;
        userService.deleteById(id);

        assertNull(userService.findById(id));
    }

    @Test public void testUserActivity() {

        final Long id = 1L;

        Set<UserActivity> userActivities = userService.userActivities(id);

        assertNotEquals(0, userActivities.size());
    }
}