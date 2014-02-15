package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AnswerServiceTest extends TestContext {

    @Test public void testFindAnswerById() {

        final Long id = 1L;

        Answer answer = answerService.findById(id);

        assertNotNull(answer);
        assertEquals(id, answer.getId());
        assertNotNull(answer.getUser());
        assertNotNull(answer.getQuestion());
    }

    @Test public void testCreateAnswer() {

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

        answer.setContent(content);
        answer.setVotes(votes);
        answer.setIsAccepted(true);
        answer.setQuestion(question);
        answer.setUser(user);

        assertNull(answer.getId());
        answerService.create(answer);
        assertNotNull(answer.getId());

        answer = answerService.findById(answer.getId());

        assertNotNull(answer);
        assertEquals(content, answer.getContent());
        assertEquals(votes, answer.getVotes());
        assertEquals(true, answer.getIsAccepted());
        assertEquals(user, answer.getUser());
        assertEquals(question, answer.getQuestion());
    }

    @Test public void testUpdateAnswer() {

        final Long id = 1L;

        Answer answer = answerService.findById(id);
        assertNotNull(answer);

        final String oldContent = answer.getContent();
        final Date created = answer.getCreated();

        // set new content
        answer.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");

        answerService.update(answer);
        answer = answerService.findById(id);

        assertEquals(id, answer.getId());
        assertEquals(created, answer.getCreated());
        assertNotEquals(oldContent, answer.getContent());
    }
}
