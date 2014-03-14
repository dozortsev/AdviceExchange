package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AnswerServiceTest extends TestContext {

    @Test public void testFindAnswerById() {

        // choose Answer Id
        final Long id = 150L;

        final Answer answer = answerService.findById(id);

        assertNotNull(answer);
    }

    @Test public void testCreateAnswer() {

        // choose random Question and User Id
        final Long qsId = 10L, userId = 10L;

        // prepare data for test of create
        final String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio, obcaecati.";
        final Integer votes = 12;

        final Answer answer = new Answer();

        // User how posted this Answer
        final User user = userService.findById(userId);
        assertNotNull(user);

        // Question how related with this Answer
        final Question question = questionService.findById(qsId);
        assertNotNull(question);

        // Answer set data
        answer.setContent(content);
        answer.setVotes(votes);
        answer.setIsAccepted(true);
        answer.setQuestion(question);
        answer.setUser(user);

        assertNull(answer.getId());
        answerService.create(answer);
        assertNotNull(answer.getId());

        // reload
        final Answer expectedAnswer = answerService.findById(answer.getId());
        assertNotNull(expectedAnswer);

        // check on the expected data
        assertEquals(answer.getContent(), content);
        assertEquals(answer.getVotes(), votes);
        assertTrue(answer.getIsAccepted());
        assertTrue(answerService.findAnswersByUserId(userId).contains(answer));
        assertTrue(answerService.findAnswersByQuestionId(qsId).contains(answer));
    }

    @Ignore
    @Test public void testDeleteAnswer() {

        // choose Answer Id
        final Long id = 141L;

        final Answer answer = answerService.findById(id);

        assertNotNull(answer);
        answerService.delete(answer);

        assertNull(answerService.findById(id));
    }

    @Test public void testUpdateAnswer() {

        // choose Answer Id
        final Long id = 141L;

        final Answer answer = answerService.findById(id);
        assertNotNull(answer);

        final String oldContent = answer.getContent();
        final Date created = answer.getCreated();

        // set new content
        answer.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");

        // persist changes
        answerService.update(answer);

        // reload
        final Answer expectedAnswer = answerService.findById(id);

        assertEquals(expectedAnswer.getId(), id);
        assertEquals(expectedAnswer.getCreated(), created);
        assertNotEquals(expectedAnswer.getContent(), oldContent);
    }
}
