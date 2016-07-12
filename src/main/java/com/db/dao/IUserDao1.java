package com.db.dao;

import com.db.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Pavel Neizhmak
 */
@Component
public interface IUserDao1 {

    void createNewUser(String login, String password, String email);

    User getByName(String name);

    void update(User user);

    void updateLoginTs(Long id);

    void deleteById(Long id);

    Collection getAll();

    String getPassword(Long id);
}
