package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Long, Comment> {

    List<Comment> findByQuestionId(long questionId);
}
