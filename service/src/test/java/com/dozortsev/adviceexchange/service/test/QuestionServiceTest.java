package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testFindQuestionById() {

        // choose exist Question Id
        final Long id = 2L;

        // expected User id
        final Long userId = 86L;

        final Question question = questionService.findById(id);

        assertNotNull(userService.findById(userId));
        assertEquals(3, questionService.findQuestionsByUserId(userId).size());
        assertEquals(1, answerService.findAnswersByUserId(userId).size());

        assertNotNull(question);
        assertEquals(Type.QUESTION, question.getType());
        assertTrue(userService.userActivities(userId).contains(question));
        assertTrue(questionService.findQuestionsByUserId(userId).contains(question));
    }

    @Test public void testCreateQuestion() {

        // choose exist User
        final User user = userService.findById(68L);

        assertEquals(0, userService.userActivities(user.getId()).size());

        // prepare Question data
        final String name = "Do all kids who are available for adoption get adopted?";
        final String content = "According to the National Council on Adoption, there is no way to know for sure.";
        final List<Tag> tags = Arrays.asList(tagService.findById(1L), tagService.findById(5L), tagService.findById(6L));

        final Question question = new Question(user, content, name, tags);

        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertEquals(Type.QUESTION, expectQuestion.getType());
        assertEquals(name, expectQuestion.getName());
        assertEquals(content, expectQuestion.getContent());
        assertEquals(tags, expectQuestion.getTags());
        assertEquals(1, userService.userActivities(user.getId()).size());
        assertTrue(userService.userActivities(user.getId()).contains(expectQuestion));
    }

    @Test public void testDeleteQuestion() {

        // choose exist Question id
        final Long id = 4L;

        final Question question = questionService.findById(id);
        assertNotNull(question);
        assertEquals(4, question.getTags().size());

        questionService.delete(question);

        assertNull(questionService.findById(id));

        // referenced Answers should be deleted
        assertNull(answerService.findById(21L));

        // referenced Tags should not deleted
        assertNotNull(tagService.findById(4L));
        assertNotNull(tagService.findById(1L));
        assertNotNull(tagService.findById(15L));
        assertNotNull(tagService.findById(11L));
    }

    @Ignore
    @Test public void testLoadQuestions() {

        LinkedHashMap<Question, Integer> map = questionService.loadAll(0, 5);
        assertEquals(5, map.size());

        map = questionService.loadAll(5, 10);
        assertEquals(10, map.size());
    }

    @Test public void testDeactivateQuestion() {

        // choose exist User id
        final Long userId = 90L;

        // expected referenced Question
        final Question question = questionService.findById(14L);

        assertTrue(questionService.findQuestionsByUserId(userId).contains(question));

        // deactivate
        question.canActive(Boolean.FALSE);
        questionService.update(question);

        assertFalse(questionService.findQuestionsByUserId(userId).contains(question));
    }

    @Test public void testUpdateQuestion() {

        // choose exist Question
        final Question question = questionService.findById(1L);

        final Tag removedTag = question.getTags().get(1);
        assertTrue(question.getTags().remove(removedTag));  // remove one tag
        final String oldName = question.getName();

        // update name and add another Tag
        question.setName("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, animi!");
        final Tag newTag = tagService.findById(13L);
        question.getTags().add(newTag);

        questionService.update(question);

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertNotEquals(oldName, expectQuestion.getName());
        assertTrue(expectQuestion.getTags().contains(newTag));
        assertFalse(expectQuestion.getTags().contains(removedTag));
    }

    @Test public void testFindQuestionsByUserId() {

        // choose exist user Id
        final Long userId = 10L;

        final Set<Question> userQuestions = questionService.findQuestionsByUserId(userId);
        assertNotNull(userQuestions);
        assertEquals(1, userQuestions.size());  // expected size should be 1

        for (Question question : userQuestions)
            assertEquals(Type.QUESTION, question.getType());
    }

    @Ignore("Work only on MySQL")
    @Test public void testFindQuestionsByTagsId() {

        final Set<Question> questions = questionService.findQuestionsByTags("Medicine", "Etymology");

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
}