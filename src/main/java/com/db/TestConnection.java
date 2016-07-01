package com.db;

import com.db.dao.IUserDAO;
import com.db.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

/**
 * @author Pavel Neizhmak
 */
public class TestConnection {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db-config.xml");

        IUserDAO userDao = ctx.getBean("userDAO", IUserDAO.class);

        User user2create = new User();
        user2create.setUsername("test_username");
        user2create.setLastLoginDate(new Date(new java.util.Date().getTime()));
        user2create.setUserCreatedDate(new Date(new java.util.Date().getTime()));
        user2create.setAccountStatusId(1);

        userDao.createNewUser(user2create);

        System.out.println("User creation done !");

        User user2read = userDao.getById(5L);
        System.out.println("User retrieved: " + user2read);
    }
}
