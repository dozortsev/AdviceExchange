package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.Tag;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

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

        // new User asked 1 question
        final Question question = new Question(
                "dolore eum ex explicabo fuga harum", user, 1,
                "consectetur adipisicing elit. Aut blanditiis dolore eum ex explicabo"
        );
        // add to Question 1 Tag
        question.setTags(new ArrayList<Tag>() {{ add(tagService.findById(4L)); }});

        // set this Question to User
        user.setQuestions(new ArrayList<Question>(){{ add(question); }});

        // try to create new User
        assertNull(user.getId());
        userService.create(user);
        assertNotNull(user.getId());

        // also save Question
        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        user = userService.findById(user.getId());
        assertNotNull(user);

        // check on the expected data
        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
        assertEquals(aboutMe, user.getAboutMe());
        assertEquals(location, user.getLocation());
        assertEquals(site, user.getSite());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(reputation, user.getReputation());

        Set<Question> userQuestions = questionService.findQuestionByUserId(user.getId());
        assertEquals(1, userQuestions.size());
        assertTrue(userQuestions.contains(question));
    }

    @Test public void testUpdateUser() {

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