package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired private String findUserByLogin;

    protected UserDaoImpl() {
        super(User.class);
    }

    @Override public User findByLogin(String login) {
        try {
            log.info(format("Finding %s by Login: %s", getEntityClass(), login));
            User user = (User) getCurrentSession().createSQLQuery(findUserByLogin)
                        .addEntity(getEntityClass()).setString("login", login)
                        .uniqueResult();

            if (user != null) {
                log.info("Successful found");
                return user;
            }
            log.info(format("%s not exist", getEntityClass()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }
}
