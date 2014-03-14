package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Question;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testUserQuestions() {

        // choose random Question Id
        final Long userId = 1L;

        final Set<Question> userQuestions = questionService.findQuestionsByUserId(userId);
        assertFalse(userQuestions.isEmpty());

        for (Question question : userQuestions) {

            Question expectQuestion = questionService.findById(question.getId());

            assertEquals(expectQuestion, question);
        }
    }

    @Test public void testFindQuestionById() {

        // choose random Question Id
        final Long id = 2L;

        Question question = questionService.findById(id);

        assertNotNull(question);
    }

    @Test public void testFindQuestionsByTagsId() {

        final Long[] arrTagId = {10L, 12L};

        final Set<Question> questions = questionService.findQuestionsByTagsId(arrTagId);

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
    }
}