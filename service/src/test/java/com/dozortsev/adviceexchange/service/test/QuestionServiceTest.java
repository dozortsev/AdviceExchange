package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.*;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testUserQuestions() {

        // choose random Question Id
        final Long userId = 26L;

        Set<Question> userQuestions = questionService.findQuestionByUserId(userId);

        assertNotNull(userQuestions);
        assertFalse(userQuestions.isEmpty());

        for (Question question : userQuestions) {

            Question expectQuestion = questionService.findById(question.getId());

            assertEquals(expectQuestion, question);
        }
    }

    @Test public void testFindQuestionById() {

        // choose random Question Id
        final Long id = 1L;

        Question question = questionService.findById(id);

        assertNotNull(question);
        assertEquals(id, question.getId());
    }

    @Test public void testCreateQuestion() {

        // prepare data for test of create
        final Integer votes = 2;
        final String name = "Lorem ipsum dolor sit amet, consectetur adipisicing.";
        final String content = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet deleniti dolorem dolores dolorum enim id obcaecati quae quam quasi rem?";

        Question question = new Question();

        // User how asked this Question
        User user = userService.findById(10L);

        // first Answer
        User awUser1 = userService.findById(5L);
        assertNotNull(awUser1);
        String awContent1 = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet, cupiditate!";
        Answer answer1 = new Answer(
                question, 2, awUser1, awContent1, false
        );

        // second Answer (was accepted)
        User awUser2 = userService.findById(6L);
        assertNotNull(awUser2);
        String awContent2 = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error iure labore optio perspiciatis quos? Voluptates.";
        Answer answer2 = new Answer(
                question, 5, awUser2, awContent2, true
        );

        // Question have 2 tags
        Tag tag1 = tagService.findById(1L);
        assertNotNull(tag1);
        Tag tag2 = tagService.findById(2L);
        assertNotNull(tag2);

        // also Question have 1 comment
        Comment comment = new Comment(question, awUser1, "Lorem ipsum dolor sit.");

        // set another data
        question.setName(name);
        question.setUser(user);
        question.setContent(content);
        question.setVotes(votes);

        question.getComments().add(comment);
        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        question.getTags().add(tag1);
        question.getTags().add(tag2);

        // try to create new Question
        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());

        // reload
        question = questionService.findById(question.getId());
        assertNotNull(question);

        // check on the expected data
        assertEquals(name, question.getName());
        assertEquals(content, question.getContent());
        assertEquals(votes, question.getVotes());

        Set<Comment> questionComments = commentService.findCommentsByQuestionId(question.getId());
        assertEquals(1, questionComments.size());
        assertTrue(questionComments.contains(comment));
    }

    @Test public void testDeleteQuestion() {

        // choose random Question Id
        final Long id = 15L;

        questionService.deleteById(id);
        assertNull(questionService.findById(id));

        // With Question should be deleted all related Comments
        assertTrue(commentService.findCommentsByQuestionId(id).isEmpty());
    }
}
