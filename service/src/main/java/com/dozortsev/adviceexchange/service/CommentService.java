package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Comment;

import java.util.Set;

public interface CommentService extends GenericService<Comment> {

    Set<Comment> findByQuestionId(int questionId);
}
