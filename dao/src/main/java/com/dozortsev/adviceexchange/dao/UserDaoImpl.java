package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired private String findUserByLogin;

    @Autowired private String findUserActivity;

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @Override public User findUserByLogin(String login) {

        return (User) getCurrentSession().createSQLQuery(findUserByLogin)
                .addEntity(getEntityClass()).setString("login", login)
                .uniqueResult();
    }

    @Override public List<UserActivity> userActivities(Long id) {

        return getCurrentSession()
                .createSQLQuery(findUserActivity)
                .setLong("userId", id)
                .list();


    }
}
