package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Comment;

import java.util.Set;

public interface CommentService extends GenericService<Long, Comment> {

    Set<Comment> findCommentsByQuestionId(Long questionId);
}
