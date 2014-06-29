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
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
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

    @Override public int totalCount(String name) {
        final long start = nanoTime();
        int totalCount = 0;
        try {
            totalCount = getDao().totalCount(name);
            log.info(format("Total count of Users: %d by Name: '%s'", totalCount, name));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return totalCount;
    }

    @Override public Set<User> findByName(String name, int offset) {
        final long start = nanoTime();
        LinkedHashSet<User> users = new LinkedHashSet<>();
        try {
            log.info(format("Load Users by name: '%s'; from: %d; row count: 36", name, offset));
            users.addAll(getDao().findByName(name, offset));
            log.info(format("Set of Users have size: %d", users.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return users;
    }

    @Override public User findByLogin(String login) {
        final long start = nanoTime();
        try {
            log.info(format("Find User by Login: %s", login));
            User user = getDao().findByLogin(login);

            if (user != null) {
                log.info("Success found");
                return user;
            }
            log.info("User not exist");
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return null;
    }

    @Override public Set<UserActivity> userActivities(long id) {
        final long start = nanoTime();
        Set<UserActivity> userActivities = new LinkedHashSet<>();
        try {
            log.info(format("User activity by ID: %d", id));
            userActivities.addAll(getDao().userActivities(id));
            log.info(format("Set of Activity have size: %d", userActivities.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return userActivities;
    }
}
