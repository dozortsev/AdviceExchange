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

    private @Autowired UserDao userDao;

    @Override public UserDao getDao() {
        return userDao;
    }

    public UserServiceImpl() {
        setEntityClass(User.class);
    }

    @Override public Set<User> findUsersByName(String name, Integer offset) {
        LinkedHashSet<User> users = new LinkedHashSet<>();
        try {
            log.info(format("Load Users by name: '%s'; from: %d; row count: 36", name, offset));
            users.addAll(getDao().findUsersByName(name, offset));
            log.info(format("Set of Users have size: %d", users.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return users;
    }

    @Override public User findUserByLogin(String login) {
        try {
            log.info(format("Find User by Login: %s", login));
            User user = getDao().findUserByLogin(login);

            if (user != null) {
                log.info("Success found");
                return user;
            }
            log.info("User not exist");
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return null;
    }

    @Override public Set<UserActivity> userActivities(Long id) {
        Set<UserActivity> userActivities = new LinkedHashSet<>();
        try {
            log.info(format("User activity by ID: %d", id));
            userActivities.addAll(getDao().userActivities(id));
            log.info(format("Set of Activity have size: %d", userActivities.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return userActivities;
    }
}
