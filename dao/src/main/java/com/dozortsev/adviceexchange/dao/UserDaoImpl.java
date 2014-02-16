package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class UserDaoImpl extends GenericDaoImpl<Long, User> implements UserDao {

    @Autowired private String findUserByLogin;

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override public User findByLogin(String login) {
        try {
            log.info(format("Find %s by Login: %s", getEntityClass().getSimpleName(), login));
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
