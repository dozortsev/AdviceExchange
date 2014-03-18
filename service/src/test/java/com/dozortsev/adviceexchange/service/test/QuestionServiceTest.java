package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Question;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testUserQuestions() {

        // choose exist Question Id
        final Long userId = 1L;

        final Set<Question> userQuestions = questionService.findQuestionsByUserId(userId);
        assertNotNull(userQuestions);
        assertFalse(userQuestions.isEmpty());

        for (Question question : userQuestions) {

            Question expectQuestion = questionService.findById(question.getId());

            assertEquals(expectQuestion, question);
        }
    }

    @Test public void testFindQuestionById() {

        // choose exist Question Id
        final Long id = 2L;

        Question question = questionService.findById(id);

        assertNotNull(question);
    }

    @Ignore("Work only on MySQL")
    @Test public void testFindQuestionsByTagsId() {

        final Set<Question> questions = questionService.findQuestionsByTags("Medicine", "Etymology");

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
}