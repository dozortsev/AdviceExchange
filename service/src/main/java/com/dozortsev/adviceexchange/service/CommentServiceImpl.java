package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.CommentDao;
import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class CommentServiceImpl extends GenericServiceImpl<Long, Comment> implements CommentService {

    @Autowired private CommentDao commentDao;

    @Override public CommentDao getDao() {
        return commentDao;
    }
}
