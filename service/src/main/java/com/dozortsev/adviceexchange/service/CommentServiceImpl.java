package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.CommentDao;
import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class CommentServiceImpl extends GenericServiceImpl<Long, Comment> implements CommentService {

    private @Autowired CommentDao commentDao;

    @Override public CommentDao getDao() {
        return commentDao;
    }

    public CommentServiceImpl() {
        setEntityClass(Comment.class);
    }

    @Transactional(readOnly = true)
    @Override public Set<Comment> findCommentsByQuestionId(Long questionId) {
        Set<Comment> comments = new LinkedHashSet<>();
        try {
            log.info(format("Find Comments by Question ID: %d", questionId));
            comments.addAll(getDao().findCommentsByQuestionId(questionId));
            log.info(format("Set of Comments have size: %d", comments.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return comments;
    }
}
