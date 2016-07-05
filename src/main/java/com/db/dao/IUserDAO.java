package com.db.dao;

import com.db.model.User;

import java.util.Collection;

/**
 * @author Pavel Neizhmak
 */
public interface IUserDao {

    void createNewUser(String login, String password, String email);

    User getByName(String name);

    void update(User user);

    void updateLoginTs(Long id);

    void deleteById(Long id);

    Collection getAll();

    String getPassword(Long id);
}
