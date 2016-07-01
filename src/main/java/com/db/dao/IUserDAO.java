package com.db.dao;

import com.db.model.User;

import java.util.Collection;

/**
 * @author Pavel Neizhmak
 */
public interface IUserDao {

    void createNewUser(User user);

    User getById(Long id);

    void update(User user);

    void deleteById(Long id);

    Collection getAll();
}
