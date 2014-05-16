package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    private @Autowired String findUsersByName;

    private @Autowired String userActivityQuery;

    private @Autowired JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @Override public Integer totalCount(String name) {

        return getCurrentSession().createCriteria(getEntityClass())
                .add(like("name", "%" + name + "%"))
                .add(eq("enabled", Boolean.TRUE))
                .setProjection(Projections.rowCount())
                .uniqueResult()
                .hashCode();
    }

    @Override public List<User> findByName(String name, Integer offset) {

        return getCurrentSession().createSQLQuery(findUsersByName)
                .addEntity(getEntityClass())
                .setString("username", "%" + name + "%")
                .setInteger("offset", offset)
                .list();
    }

    @Override public User findByLogin(String login) {

        return (User) getCurrentSession().createCriteria(getEntityClass())
                .add(eq("email", login))
                .add(eq("enabled", Boolean.TRUE))
                .uniqueResult();
    }

    @Override public List<UserActivity> userActivities(Long id) {

        return jdbcTemplate.query(userActivityQuery, new Object[]{id}, new RowMapper<UserActivity>() {
            @Override public UserActivity mapRow(final ResultSet rs, int i) throws SQLException {
                UserActivity act = null;

                while (rs.next()) {
                    switch (Type.valueOf(rs.getString("ua_type"))) {
                        case ANSWER:
                            act = new Answer() {{
                                setId(rs.getLong("ua_id"));
                                setVotes(rs.getInt("asw_votes"));
                                canActive(rs.getBoolean("ua_active"));
                                canAccept(rs.getBoolean("asw_accepted"));
                                setContent(rs.getString("ua_content"));
                                setCreated(rs.getDate("ua_created"));
                            }};
                            break;
                        case QUESTION:
                            act = new Question() {{
                                setId(rs.getLong("ua_id"));
                                setVotes(rs.getInt("asw_votes"));
                                canActive(rs.getBoolean("ua_active"));
                                setTitle(rs.getString("qs_title"));
                                setContent(rs.getString("ua_content"));
                                setCreated(rs.getDate("ua_created"));
                            }};
                            break;
                        case COMMENT:
                            act = new Comment() {{
                                setId(rs.getLong("ua_id"));
                                canActive(rs.getBoolean("ua_active"));
                                setContent(rs.getString("ua_content"));
                                setCreated(rs.getDate("ua_created"));
                            }};
                            break;
                    }
                }
                return act;
            }
        });
    }
}
