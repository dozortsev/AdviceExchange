package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest extends TestContext {

    @Test public void testFindCommentById() {

        // choose random Comment Id
        final Long id = 21L;

        Comment comment = commentService.findById(id);

        assertNotNull(comment);
        assertEquals(id, comment.getId());
    }

    @Ignore
    @Test public void testDeleteComment() {

        // choose Comment Id
        final Long id = 25L;

        Comment comment = commentService.findById(id);
        assertNotNull(comment);

        // try to delete Comment
        commentService.delete(comment);
        assertNull(commentService.findById(id));
    }
}
