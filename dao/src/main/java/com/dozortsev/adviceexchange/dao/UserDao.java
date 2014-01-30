package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;

public interface UserDao extends GenericDao<Long, User> {

    public User findByLogin(String login);
}
