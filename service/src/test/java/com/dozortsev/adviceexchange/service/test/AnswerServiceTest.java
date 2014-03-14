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

        Answer answer = answerService.findById(id);

        assertNotNull(answer);
        assertEquals(id, answer.getId());
    }

    @Test public void testCreateAnswer() {

        // choose random Question and User Id
        final Long qsId = 10L, userId = 10L;

        // prepare data for test of create
        final String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio, obcaecati.";
        final Integer votes = 12;

        Answer answer = new Answer();

        // User how posted this Answer
        User user = userService.findById(userId);
        assertNotNull(user);

        // Question how related with this Answer
        Question question = questionService.findById(qsId);
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
        answer = answerService.findById(answer.getId());

        assertNotNull(answer);
        assertEquals(content, answer.getContent());
        assertEquals(votes, answer.getVotes());
        assertEquals(true, answer.getIsAccepted());
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

        Answer answer = answerService.findById(id);
        assertNotNull(answer);

        final String oldContent = answer.getContent();
        final Date created = answer.getCreated();

        // set new content
        answer.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");

        answerService.update(answer);
        answer = answerService.findById(id);

        assertEquals(id, answer.getId());
        assertEquals(created, answer.getCreated()); // should be not updated
        assertNotEquals(oldContent, answer.getContent());
    }
}
