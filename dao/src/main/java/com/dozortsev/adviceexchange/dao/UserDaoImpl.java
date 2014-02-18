package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired private String findUserByLogin;

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override public User findByLogin(String login) {

        return (User) getCurrentSession().createSQLQuery(findUserByLogin)
                .addEntity(getEntityClass()).setString("login", login)
                .uniqueResult();
    }
}
