package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class CommentDaoImpl extends GenericDaoImpl<Long, Comment> implements CommentDao {

    @Autowired private String findCommentsByQuestionId;

    public CommentDaoImpl() {
        setEntityClass(Comment.class);
    }

    @SuppressWarnings("unchecked")
    @Override public List<Comment> findByQuestionId(long questionId) {

        return getCurrentSession().createSQLQuery(findCommentsByQuestionId)
                .addEntity(getEntityClass()).setLong("questionId", questionId)
                .list();
    }
}
