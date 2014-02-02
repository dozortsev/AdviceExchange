package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Long, Comment> implements CommentDao {

    protected CommentDaoImpl() {
        super(Comment.class);
    }
}
