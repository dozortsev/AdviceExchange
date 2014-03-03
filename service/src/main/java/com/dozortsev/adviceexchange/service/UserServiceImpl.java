package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.UserDao;
import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, readOnly = true)
@Service
public class UserServiceImpl extends GenericServiceImpl<Long, User> implements UserService {

    @Autowired private UserDao userDao;

    @Override public UserDao getDao() {
        return userDao;
    }

    public UserServiceImpl() {
        setEntityClass(User.class);
    }

    @Override public User findUserByLogin(String login) {
        try {
            log.info(format("Find %s by Login: %s", getEntityClass(), login));
            User user = getDao().findUserByLogin(login);

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

    @Override public Set<UserActivity> userActivities(Long id) {
        Set<UserActivity> userActivities = new LinkedHashSet<>();
        try {
            log.info(format("User activity by Id: %s", id));
            userActivities.addAll(getDao().userActivities(id));
            log.info(format("Set have size: %s", userActivities.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return userActivities;
    }
}
