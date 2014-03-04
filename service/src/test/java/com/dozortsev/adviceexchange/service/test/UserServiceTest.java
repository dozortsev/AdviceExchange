package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

import static java.util.Arrays.asList;
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

    @Test public void testCreateUser() {

        // prepare User for test of create
        final Integer age = 25, reputation = 1;
        final String name = "Lukas Eder", aboutMe = "Java Object Oriented Querying",
                location = "Switzerland, Zurich", site = "github.com/lukaseder",
                email = "lukas.eder@gmail.com", password = "lukas_dev";

        final User user = new User(name, age, aboutMe, location, site, email, password, reputation);

        // new User asked 1 question
        final Question question = new Question(
                "dolore eum ex explicabo fuga harum", user, 1,
                "consectetur adipisicing elit. Aut blanditiis dolore eum ex explicabo"
        );
        // add to Question 1 Tag
        question.setTags(asList(tagService.findById(4L)));

        // Question have 1 accepted Answer
        final Answer answer = new Answer(
                question, 10, userService.findById(50L), "consectetur adipisicing elit Content Aut blanditiis dolore eum ex explicabo", true
        );
        question.setAnswers(asList(answer));

        // set this Question to User
        user.setQuestions(asList(question));

        // try to create new User
        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        // also save Question
        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        final User expectUser = userService.findById(user.getId());
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

        final Set<Question> userQuestions = questionService.findQuestionsByUserId(expectUser.getId());
        assertEquals(1, userQuestions.size());
        assertTrue(userQuestions.contains(question));

        final Set<Answer> userAnswers = answerService.findAnswersByUserId(expectUser.getId());
        assertEquals(1, userAnswers.size());
        assertTrue(userAnswers.contains(answer));
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

    @Test public void testDeleteUser() {

        // choose random User Id
        final Long userId = 3L;
        final User user = userService.findById(userId);

        assertNotNull(user);
        userService.delete(user);

        // try to found after delete operation
        assertNull(userService.findById(userId));

        // With User should be delete all him Questions
        assertTrue(questionService.findQuestionsByUserId(userId).isEmpty());
        // Answers
        assertTrue(answerService.findAnswersByUserId(userId).isEmpty());
        // also the reference Badges
        assertTrue(badgeService.findBadgesByUserId(userId).isEmpty());
    }

    @Test public void testDeleteUserById() {

        final Long userId = 4L;
        userService.deleteById(userId);

        // try to found after delete operation
        assertNull(userService.findById(userId));

        // With User should be delete all him Questions
        assertTrue(questionService.findQuestionsByUserId(userId).isEmpty());
        // Answers
        assertTrue(answerService.findAnswersByUserId(userId).isEmpty());
        // also the reference Badges
        assertTrue(badgeService.findBadgesByUserId(userId).isEmpty());
    }
}