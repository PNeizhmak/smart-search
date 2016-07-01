package com.db;

import com.db.dao.IUserDao;
import com.db.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

/**
 * @author Pavel Neizhmak
 */
public class TestConnection {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db-config.xml");

        IUserDao userDao = ctx.getBean("userDAO", IUserDao.class);

        User user = new User();
        user.setUsername("test_username");
        user.setLastLoginDate(new Date(new java.util.Date().getTime()));
        user.setUserCreatedDate(new Date(new java.util.Date().getTime()));
        user.setAccountStatusId(1);

        userDao.createNewUser(user);

        System.out.println("User creation done !");
    }
}
