package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    private @Autowired String findUsersByName;

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

        return getCurrentSession().createCriteria(UserActivity.class)
                .add(eq("user.id", id))
                .setResultTransformer(DISTINCT_ROOT_ENTITY)
                .list();
    }
}
