package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentServiceTest extends TestContext {

    @Test public void testLoadCommentById() {

        // choose random Comment Id
        final Long id = 1L;

        Comment comment = commentService.findById(id);

        assertNotNull(comment);
        assertEquals(id, comment.getId());
        assertNotNull(comment.getUser());
        assertNotNull(comment.getQuestion());
    }
}
