package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired private String findUserByLogin;

    @Autowired private String findUserByName;

//    @Autowired private String findUserActivity;

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @Override public User findUserByLogin(String login) {

        return (User) getCurrentSession().createSQLQuery(findUserByLogin)
                .addEntity(getEntityClass()).setString("login", login)
                .uniqueResult();
    }

    @Override public List<User> findUsersByName(String name) {

        return getCurrentSession().createSQLQuery(findUserByName)
                .addEntity(getEntityClass())
                .setResultTransformer(DISTINCT_ROOT_ENTITY)
                .setString("userName", "%" + name + "%")
                .list();
    }

    @Override public List<UserActivity> userActivities(Long id) {

        return getCurrentSession().createCriteria(UserActivity.class)
                .add(Restrictions.eq("user.id", id))
                .list();
    }
}
