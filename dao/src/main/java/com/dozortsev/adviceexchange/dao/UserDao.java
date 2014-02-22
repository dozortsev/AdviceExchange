package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;

public interface UserDao extends GenericDao<Long, User> {

    public User findUserByLogin(String login);
}
