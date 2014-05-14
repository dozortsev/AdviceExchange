package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
import com.dozortsev.adviceexchange.domain.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest extends TestContext {

    @Test public void testFindCommentById() {

        // choose exist Comment id
        final Long id = 52L;

        // expected Question id
        final Long questionId = 7L;

        // expected User id
        final Long userId = 88L;

        final Comment comment = commentService.findById(id);

        assertNotNull(comment);
        assertEquals(Type.COMMENT, comment.getType());
        assertTrue(userService.userActivities(userId).contains(comment));
        assertTrue(commentService.findByQuestionId(questionId).contains(comment));
    }

    @Test public void testCreateComment() {

        final Long userId = 12L, questionId = 10L;

        final String content = "Lorem ipsum dolor sit.";

        // prepare data for test
        final Comment comment = new Comment(userService.findById(userId), content, questionService.findById(questionId));

        assertNull(comment.getId());
        commentService.create(comment);
        assertNotNull(comment.getId());

        // reload
        final Comment expectedComment = commentService.findById(comment.getId());

        assertEquals(Type.COMMENT, expectedComment.getType());
        assertEquals(content, expectedComment.getContent());
        assertTrue(userService.userActivities(userId).contains(expectedComment));
        assertTrue(commentService.findByQuestionId(questionId).contains(expectedComment));
    }

    @Test public void testDeleteComment() {

        // choose exist Comment id
        final Long id = 51L;

        // expected Question id and User id
        final Long questionId = 7L;
        final Long userId = 36L;

        final Comment comment = commentService.findById(id);
        assertNotNull(comment);
        assertEquals(Type.COMMENT, comment.getType());
        assertTrue(commentService.findByQuestionId(questionId).contains(comment));
        assertTrue(userService.userActivities(userId).contains(comment));

        commentService.delete(comment);

        assertNull(commentService.findById(id));
        assertFalse(commentService.findByQuestionId(questionId).contains(comment));
        assertFalse(userService.userActivities(userId).contains(comment));
    }
}
