package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.Type;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AnswerServiceTest extends TestContext {

    @Test public void testFindAnswerById() {

        // choose exist Answer Id
        final Long id = 21L;

        // expected User id
        final Long userId = 19L;

        // expected Question id
        final Long questionId = 4L;

        final Answer answer = answerService.findById(id);

        assertNotNull(answer);
        assertEquals(Type.ANSWER, answer.getType());
        assertTrue(userService.userActivities(userId).contains(answer));
        assertTrue(answerService.findAnswersByUserId(userId).contains(answer));
        assertTrue(answerService.findAnswersByQuestionId(questionId).contains(answer));
    }

    @Test public void testCreateAnswer() {

        // choose exist Question and User
        final User user = userService.findById(10L);
        final Question question = questionService.findById(10L);

        // prepare data for test of create
        final String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio, obcaecati.";

        final Answer answer = new Answer(user, content, question, true);

        assertNull(answer.getId());
        answerService.create(answer);
        assertNotNull(answer.getId());

        // reload
        final Answer expectAnswer = answerService.findById(answer.getId());
        assertNotNull(expectAnswer);

        // check on the expected data
        assertEquals(Type.ANSWER, expectAnswer.getType());
        assertEquals(content, expectAnswer.getContent());
        assertTrue(expectAnswer.getVotes().equals(0));
        assertTrue(expectAnswer.getIsAccepted());
        assertTrue(answerService.findAnswersByUserId(user.getId()).contains(expectAnswer));
        assertTrue(answerService.findAnswersByQuestionId(question.getId()).contains(expectAnswer));
    }

    @Test public void testDeleteAnswer() {

        // choose Answer id
        final Long id = 36L;

        // expected Question id
        final Long questionId = 1L;

        // expected User id
        final Long userId = 78L;

        final Answer answer = answerService.findById(id);
        assertNotNull(answer);
        assertEquals(Type.ANSWER, answer.getType());
        assertTrue(answerService.findAnswersByQuestionId(questionId).contains(answer));
        assertTrue(answerService.findAnswersByUserId(userId).contains(answer));

        answerService.delete(answer);

        assertNull(answerService.findById(id));
        assertFalse(answerService.findAnswersByQuestionId(questionId).contains(answer));
        assertFalse(answerService.findAnswersByUserId(userId).contains(answer));
    }

    @Test public void testUpdateAnswer() {

        // choose Answer
        final Answer answer = answerService.findById(22L);

        final String oldContent = answer.getContent();
        final Date created = answer.getCreated();

        // set new content
        answer.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");

        // persist changes
        answerService.update(answer);

        // reload
        final Answer expectedAnswer = answerService.findById(answer.getId());

        assertEquals(created, expectedAnswer.getCreated());
        assertNotEquals(oldContent, expectedAnswer.getContent());
    }
}
