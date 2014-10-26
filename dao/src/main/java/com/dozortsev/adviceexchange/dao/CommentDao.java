package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TComment;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Comment;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord;

import java.util.List;

public interface CommentDao extends GenericDao<CommentRecord, TComment> {

    List<Comment> findByQuestionId(int questionId);
}
