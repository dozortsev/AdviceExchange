package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TComment;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Comment;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.CommentRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType.COMMENT;
import static org.jooq.impl.DSL.sum;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class CommentDaoImpl extends GenericDaoImpl<CommentRecord, TComment> implements CommentDao {

    public CommentDaoImpl() {
        setTable(c);
        setIdField(c.ID);
    }

    @Override public List<Comment> findByQuestionId(int questionId) {

        return dsl.select(c.ID, ua.CONTENT, ua.CREATED, sum(v.SCORE).as("totalCount"))
                .from(c)
                    .leftOuterJoin(ua).on(ua.ID.eq(c.ID))
                    .join(v).on(v.ACTIVITY_ID.eq(ua.ID))
                .where(ua.TYPE.eq(COMMENT))
                    .and(c.QT_ID.eq(questionId))
                .groupBy(v.ID)
                .orderBy(ua.CREATED)

                .fetchInto(Comment.class);
    }
}
