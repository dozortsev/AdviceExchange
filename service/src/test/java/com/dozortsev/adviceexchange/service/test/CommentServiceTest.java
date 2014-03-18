package com.dozortsev.adviceexchange.service.test;

import com.dozortsev.adviceexchange.domain.Comment;
import com.dozortsev.adviceexchange.domain.Question;
import com.dozortsev.adviceexchange.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceTest extends TestContext {

    @Test public void testCreateComment() {

        final Long userId = 12L, questionId = 10L;

        final User user = userService.findById(userId);
        assertNotNull(user);
        final Question question = questionService.findById(questionId);
        assertNotNull(question);
        final String content = "Lorem ipsum dolor sit.";

        // prepare data for test
        final Comment comment = new Comment(user, content, question);

        assertNull(comment.getId());
        commentService.create(comment);
        assertNotNull(comment.getId());

        // reload
        final Comment expectedComment = commentService.findById(comment.getId());

        // check on the expected data
        assertEquals(expectedComment.getContent(), content);
    }
}
