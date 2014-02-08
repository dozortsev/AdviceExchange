package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class CommentDaoImpl extends GenericDaoImpl<Long, Comment> implements CommentDao {

    protected CommentDaoImpl() {
        this.entityClass = Comment.class;
    }
}
