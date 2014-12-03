package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Tag;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testFindQuestionById() {

        // choose exist Question Id
        final int id = 2;

        // expected User id
        final int userId = 86;

        final Question question = questionService.findById(id);

        assertNotNull(userService.findById(userId));
        assertEquals(3, questionService.findByUserId(userId).size());
        assertEquals(1, answerService.findByUserId(userId).size());

        assertNotNull(question);
        assertEquals(UserActivityType.QUESTION, question.getType());
        assertTrue(userService.userActivities(userId).contains(question));
        assertTrue(questionService.findByUserId(userId).contains(question));
    }

    @Test public void testCreateQuestion() {

        // choose exist User
        final User user = userService.findById(68);

        assertEquals(0, userService.userActivities(user.getId()).size());

        // prepare Question data
        final String title = "Do all kids who are available for adoption get adopted?";
        final String content = "According to the National Council on Adoption, there is no way to know for sure.";
        final List<Tag> tags = Arrays.asList(
                tagService.findById(1), tagService.findById(5), tagService.findById(6)
        );

        final Question question = new Question(/*title, content, user, 0, tags*/);

        assertNull(question.getId());
//        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertEquals(UserActivityType.QUESTION, expectQuestion.getType());
        assertEquals(title, expectQuestion.getTitle());
        assertEquals(content, expectQuestion.getContent());
        assertEquals(1, userService.userActivities(user.getId()).size());
        assertTrue(userService.userActivities(user.getId()).contains(expectQuestion));
    }

    @Test public void testDeleteQuestion() {

        // choose exist Question id
        final int id = 8;

        final Question question = questionService.findById(id);
        assertNotNull(question);
//        assertEquals(5, question.getTags().size());

//        questionService.delete(question);

        assertNull(questionService.findById(id));

        // referenced Answers should be deleted
        assertNull(answerService.findById(35));

        // referenced Tags should not deleted
        assertNotNull(tagService.findById(4));
        assertNotNull(tagService.findById(1));
        assertNotNull(tagService.findById(15));
        assertNotNull(tagService.findById(11));
    }

    @Test public void testDeactivateQuestion() {

        // choose exist User id
        final int userId = 90;

        // expected referenced Question
        final Question question = questionService.findById(14);

        assertTrue(questionService.findByUserId(userId).contains(question));

        // deactivate
//        question.setActive(Boolean.FALSE);
//        questionService.update(question);

        assertFalse(questionService.findByUserId(userId).contains(question));
    }

    @Test public void testUpdateQuestion() {

        // choose exist Question
        final Question question = questionService.findById(1);

//        final Tag removedTag = question.getTags().get(1);
//        assertTrue(question.getTags().remove(removedTag));  // remove one tag
        final String oldName = question.getTitle();

        // update name and add another Tag
        question.setTitle("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, animi!");
        final Tag newTag = tagService.findById(13);
//        question.getTags().add(newTag);

//        questionService.update(question);

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertNotEquals(oldName, expectQuestion.getTitle());
//        assertTrue(expectQuestion.getTags().contains(newTag));
//        assertFalse(expectQuestion.getTags().contains(removedTag));
    }

    @Test public void testFindQuestionsByUserId() {

        // choose exist user Id
        final int userId = 10;

        final Set<Question> userQuestions = questionService.findByUserId(userId);
        assertNotNull(userQuestions);
        assertEquals(1, userQuestions.size());  // expected size should be 1

        for (Question question : userQuestions)
            assertEquals(UserActivityType.QUESTION, question.getType());
    }

    @Ignore("Work only on MySQL")
    @Test public void testFindQuestionsByTagsId() {

        final Set<Question> questions = questionService.findByTags("Medicine", "Etymology");

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
}