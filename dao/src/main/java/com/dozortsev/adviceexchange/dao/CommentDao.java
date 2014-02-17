package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;

import java.util.Set;

public interface CommentDao extends GenericDao<Long, Comment> {

    Set<Comment> findCommentsByQuestionId(Long questionId);
}
