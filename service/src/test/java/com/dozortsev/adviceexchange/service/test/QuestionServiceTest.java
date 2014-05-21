package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.Tag;
import com.dozortsev.adviceexchange.domain.Type;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testFindQuestionById() {

        // choose exist Question Id
        final Long id = 2L;

        // expected User id
        final Long userId = 86L;

        final Question question = questionService.findById(id);

        assertNotNull(userService.findById(userId));
        assertEquals(3, questionService.findByUserId(userId).size());
        assertEquals(1, answerService.findByUserId(userId).size());

        assertNotNull(question);
        assertEquals(Type.QUESTION, question.getType());
        assertTrue(userService.userActivities(userId).contains(question));
        assertTrue(questionService.findByUserId(userId).contains(question));
    }

    @Test public void testCreateQuestion() {

        // choose exist User
        final User user = userService.findById(68L);

        assertEquals(0, userService.userActivities(user.getId()).size());

        // prepare Question data
        final String title = "Do all kids who are available for adoption get adopted?";
        final String content = "According to the National Council on Adoption, there is no way to know for sure.";
        final List<Tag> tags = Arrays.asList(
                tagService.findById(1L), tagService.findById(5L), tagService.findById(6L)
        );

        final Question question = new Question(title, content, user, 0, 0, tags);

        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertEquals(Type.QUESTION, expectQuestion.getType());
        assertEquals(title, expectQuestion.getTitle());
        assertEquals(content, expectQuestion.getContent());
        assertEquals(tags, expectQuestion.getTags());
        assertEquals(0, expectQuestion.getAnswerCount());
        assertEquals(0, expectQuestion.getVotes());
        assertEquals(1, userService.userActivities(user.getId()).size());
        assertTrue(userService.userActivities(user.getId()).contains(expectQuestion));
    }

    @Test public void testDeleteQuestion() {

        // choose exist Question id
        final Long id = 8L;

        final Question question = questionService.findById(id);
        assertNotNull(question);
        assertEquals(5, question.getTags().size());

        questionService.delete(question);

        assertNull(questionService.findById(id));

        // referenced Answers should be deleted
        assertNull(answerService.findById(35L));

        // referenced Tags should not deleted
        assertNotNull(tagService.findById(4L));
        assertNotNull(tagService.findById(1L));
        assertNotNull(tagService.findById(15L));
        assertNotNull(tagService.findById(11L));
    }

    @Test public void testDeactivateQuestion() {

        // choose exist User id
        final Long userId = 90L;

        // expected referenced Question
        final Question question = questionService.findById(14L);

        assertTrue(questionService.findByUserId(userId).contains(question));

        // deactivate
        question.canActive(Boolean.FALSE);
        questionService.update(question);

        assertFalse(questionService.findByUserId(userId).contains(question));
    }

    @Test public void testUpdateQuestion() {

        // choose exist Question
        final Question question = questionService.findById(1L);

        final Tag removedTag = question.getTags().get(1);
        assertTrue(question.getTags().remove(removedTag));  // remove one tag
        final String oldName = question.getTitle();

        // update name and add another Tag
        question.setTitle("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, animi!");
        final Tag newTag = tagService.findById(13L);
        question.getTags().add(newTag);

        questionService.update(question);

        // reload
        final Question expectQuestion = questionService.findById(question.getId());

        assertNotEquals(oldName, expectQuestion.getTitle());
        assertTrue(expectQuestion.getTags().contains(newTag));
        assertFalse(expectQuestion.getTags().contains(removedTag));
    }

    @Test public void testFindQuestionsByUserId() {

        // choose exist user Id
        final Long userId = 10L;

        final Set<Question> userQuestions = questionService.findByUserId(userId);
        assertNotNull(userQuestions);
        assertEquals(1, userQuestions.size());  // expected size should be 1

        for (Question question : userQuestions)
            assertEquals(Type.QUESTION, question.getType());
    }

    @Ignore("Work only on MySQL")
    @Test public void testFindQuestionsByTagsId() {

        final Set<Question> questions = questionService.findByTags("Medicine", "Etymology");

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
}