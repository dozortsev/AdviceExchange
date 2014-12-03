package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Comment;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest extends TestContext {

    @Test public void testFindCommentById() {

        // choose exist Comment id
        final int id = 521;

        // expected Question id
        final int questionId = 7;

        // expected User id
        final int userId = 88;

        final Comment comment = commentService.findById(id);

        assertNotNull(comment);
        assertEquals(UserActivityType.COMMENT, comment.getType());
        assertTrue(userService.userActivities(userId).contains(comment));
        assertTrue(commentService.findByQuestionId(questionId).contains(comment));
    }

    @Test public void testCreateComment() {

        final int userId = 12, questionId = 10;

        final String content = "Lorem ipsum dolor sit.";

        // prepare data for test
        final Comment comment = new Comment(/*userService.findById(userId), content, questionService.findById(questionId)*/);

        assertNull(comment.getId());
//        commentService.create(comment);
        assertNotNull(comment.getId());

        // reload
        final Comment expectedComment = commentService.findById(comment.getId());

        assertEquals(UserActivityType.COMMENT, expectedComment.getType());
        assertEquals(content, expectedComment.getContent());
        assertTrue(userService.userActivities(userId).contains(expectedComment));
        assertTrue(commentService.findByQuestionId(questionId).contains(expectedComment));
    }

    @Test public void testDeleteComment() {

        // choose exist Comment id
        final int id = 51;

        // expected Question id and User id
        final int questionId = 7;
        final int userId = 36;

        final Comment comment = commentService.findById(id);
        assertNotNull(comment);
        assertEquals(UserActivityType.COMMENT, comment.getType());
        assertTrue(commentService.findByQuestionId(questionId).contains(comment));
        assertTrue(userService.userActivities(userId).contains(comment));

//        commentService.delete(comment);

        assertNull(commentService.findById(id));
        assertFalse(commentService.findByQuestionId(questionId).contains(comment));
        assertFalse(userService.userActivities(userId).contains(comment));
    }
}
