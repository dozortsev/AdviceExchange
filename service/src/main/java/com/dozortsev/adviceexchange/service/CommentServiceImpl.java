package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.CommentDao;
import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class CommentServiceImpl extends GenericServiceImpl<Long, Comment> implements CommentService {

    @Autowired private CommentDao commentDao;

    @Override public CommentDao getDao() {
        return commentDao;
    }

    public CommentServiceImpl() {
        setEntityClass(Comment.class);
    }

    @Transactional(readOnly = true)
    @Override public Set<Comment> findCommentsByQuestionId(Long questionId) {
        Set<Comment> comments = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), questionId));
            comments.addAll(getDao().findCommentsByQuestionId(questionId));
            log.info(format("Set of %s have size: %s", getEntityClass(), comments.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return comments;
    }
}
