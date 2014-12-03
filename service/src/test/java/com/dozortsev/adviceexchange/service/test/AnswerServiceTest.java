package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Answer;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Vote;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType.ANSWER;
import static org.junit.Assert.*;

public class AnswerServiceTest extends TestContext {

    @Test public void testFindAnswerById() {

        // choose exist Answer Id
        final int id = 21;

        // expected User id
        final int userId = 19;

        // expected Question id
        final int questionId = 18;

        final Answer answer = answerService.findById(id);

        assertNotNull(answer);
        assertEquals(ANSWER, answer.getType());
        assertTrue(userService.userActivities(userId).contains(answer));
        assertTrue(answerService.findByUserId(userId).contains(answer));
        assertTrue(answerService.findByQuestionId(questionId).contains(answer));
    }

    @Test public void testCreateAnswer() {

        // choose exist Question and User
        final User user = userService.findById(10);
        final Question question = questionService.findById(10);

        // prepare data for test of create
        final String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio, obcaecati.";

        Answer answer = null;
        List<Vote> votes = Arrays.asList(
//                new Vote(userService.findById(11), answer, Vote.UP),
//                new Vote(userService.findById(12), answer, Vote.UP),
//                new Vote(userService.findById(30), answer, Vote.DOWN)
        );
//        answer = new Answer(user, content, votes, question);
//        answer.canAccept(true);
//
//        assertNull(answer.getId());
//        questionService.addAnswer(answer);
        assertNotNull(answer.getId());

        // reload
        final Answer expectAnswer = answerService.findById(answer.getId());
        assertNotNull(expectAnswer);

        // check on the expected data
        assertEquals(ANSWER, expectAnswer.getType());
        assertEquals(content, expectAnswer.getContent());
        assertEquals(0, expectAnswer.getTotalscore().intValue());
//        assertTrue(expectAnswer.getAccepted());
        assertTrue(answerService.findByUserId(user.getId()).contains(expectAnswer));
        assertTrue(answerService.findByQuestionId(question.getId()).contains(expectAnswer));
    }

    @Test public void testDeleteAnswer() {

        // choose Answer id
        final int id = 36;

        // expected ref Question
        final Question question = questionService.findById(17);

        // expected User id
        final int userId = 78;

        final Answer answer = answerService.findById(id);
        assertNotNull(answer);
        assertEquals(ANSWER, answer.getType());

        final Set<Answer> answers = answerService.findByQuestionId(question.getId());

        assertTrue(answers.contains(answer));
        assertTrue(answerService.findByUserId(userId).contains(answer));

        assertNull(answerService.findById(id));
        assertFalse(answerService.findByQuestionId(question.getId()).contains(answer));
        assertFalse(answerService.findByUserId(userId).contains(answer));
    }

    @Test public void testUpdateAnswer() {

        // choose Answer
        final Answer answer = answerService.findById(22);

        final String oldContent = answer.getContent();
        final Date created = answer.getCreated();

        // set new content
        answer.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");

        // persist changes
//        answerService.update(answer);

        // reload
        final Answer expectedAnswer = answerService.findById(answer.getId());

        assertEquals(created, expectedAnswer.getCreated());
        assertNotEquals(oldContent, expectedAnswer.getContent());
    }
}
