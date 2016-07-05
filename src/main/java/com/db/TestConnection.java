package com.db;

import com.db.dao.IUserDAO;
import com.db.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * @author Pavel Neizhmak
 */
public class TestConnection {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db-config.xml");
        final long testUserId = 5L;
        final String testUsername = "testUser";

        IUserDAO userDao = ctx.getBean("userDAO", IUserDAO.class);

        User user2create = new User();
        user2create.setUsername("testUser");
        user2create.setLastLoginDate(new Timestamp(new java.util.Date().getTime()));
        user2create.setUserCreatedDate(new Timestamp(new java.util.Date().getTime()));
        user2create.setAccountStatusId(1);

        userDao.createNewUser(user2create);

        System.out.println("User creation done !");

        User user2read = userDao.getByName(testUsername);
        System.out.println("User retrieved: " + user2read);

        userDao.updateLoginTs(testUserId);
        System.out.println("User login date updated successfully");
    }
}
