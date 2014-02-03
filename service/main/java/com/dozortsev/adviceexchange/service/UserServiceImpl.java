package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.UserDao;
import com.dozortsev.adviceexchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<Long, User> implements UserService {

    @Autowired private UserDao userDao;

    @Override public Class<User> getEntityClass() {
        return User.class;
    }

    @Override public UserDao getDao() {
        return userDao;
    }

    @Override public User findByLogin(String login) {
        return getDao().findByLogin(login);
    }
}
