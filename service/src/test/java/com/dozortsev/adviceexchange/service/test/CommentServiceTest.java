package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest extends TestContext {

    @Test public void testLoadCommentById() {

        // choose random Comment Id
        final Long id = 1L;

        Comment comment = commentService.findById(id);

        assertNotNull(comment);
        assertEquals(id, comment.getId());
        assertNotNull(comment.getUser());
        assertNotNull(comment.getQuestion());

        assertTrue(commentService.findCommentsByQuestionId(comment.getQuestion().getId()).contains(comment));
    }

    @Test public void testDeleteComment() {

        // choose random Comment Id
        final Long id = 5L;

        Comment comment = commentService.findById(id);
        assertNotNull(comment);

        User user = comment.getUser();
        assertNotNull(user);

        // try to delete Comment
        commentService.delete(comment);

        assertNull(commentService.findById(id));
        assertNotNull(userService.findById(user.getId()));
    }
}
