package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class CommentDaoImpl extends GenericDaoImpl<Long, Comment> implements CommentDao {

    @Autowired private String findCommentsByQuestionId;

    public CommentDaoImpl() {
        setEntityClass(Comment.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override public Set<Comment> findCommentsByQuestionId(Long questionId) {
        Set<Comment> comments = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), questionId));
            comments.addAll(
                    getCurrentSession().createSQLQuery(findCommentsByQuestionId)
                            .addEntity(getEntityClass()).setLong("questionId", questionId)
                            .list()
            );
            log.info(format("Set of %s have size: %s", getEntityClass(), comments.size()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return comments;
    }
}
