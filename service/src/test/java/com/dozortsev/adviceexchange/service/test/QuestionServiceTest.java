package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionServiceTest extends TestContext {

    @Test public void testFindQuestionById() {

        final Long id = 1L;

        Question question = questionService.findById(id);

        assertNotNull(question);
        assertEquals(id, question.getId());
    }

    @Test public void testCreateQuestion() {

        final Long userId1 = 10L, userId2 = 5L, userId3 = 6L, tagId1 = 1L, tagId2 = 12L;

        // prepare data for test of create
        Question question = new Question();

        // User how asked this Question
        User qsUser = userService.findById(userId1);

        // first Answer
        User awUser1 = userService.findById(userId2);
        assertNotNull(awUser1);
        String awContent1 = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet, cupiditate!";
        Answer answer1 = new Answer(
                question, 2, awUser1, awContent1, false
        );

        // second Answer (was accepted)
        User awUser2 = userService.findById(userId3);
        assertNotNull(awUser2);
        String awContent2 = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error iure labore optio perspiciatis quos? Voluptates.";
        Answer answer2 = new Answer(
                question, 5, awUser2, awContent2, true
        );

        // Question have 2 tags
        Tag tag1 = tagService.findById(tagId1);
        assertNotNull(tag1);
        Tag tag2 = tagService.findById(tagId2);
        assertNotNull(tag2);

        // also Question have 1 comment
        Comment comment = new Comment(question, awUser1, "Lorem ipsum dolor sit.");

        // other data
        question.setName("Lorem ipsum dolor sit amet, consectetur adipisicing.");
        question.setUser(qsUser);
        question.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet deleniti dolorem dolores dolorum enim id obcaecati quae quam quasi rem?");
        question.setVotes(2);

        question.getComments().add(comment);
        question.getAnswers().add(answer1);
        question.getAnswers().add(answer2);
        question.getTags().add(tag1);
        question.getTags().add(tag2);

        assertNull(question.getId());
        questionService.create(question);
        assertNotNull(question.getId());
    }
}
