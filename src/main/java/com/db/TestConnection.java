package com.db;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.util.PasswordUtils;
import com.util.UriUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Pavel Neizhmak
 */
public class TestConnection {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db-config.xml");
        final long testUserId = 5L;
        final String testUsername = "testUser5";

        IUserDao userDao = ctx.getBean("userDao", IUserDao.class);

        userDao.createNewUser(testUsername, "hash", null);
        System.out.println("User creation done !");

        User user2read = userDao.getByName(testUsername);
        System.out.println("User retrieved: " + user2read);

        String hashPassDb = userDao.getPassword(testUserId);
        final String hashPass = PasswordUtils.encryptPassword("hash");

        System.out.println("Passwords match: " + hashPassDb.equals(hashPass));

        userDao.updateLoginTs(testUserId);
        System.out.println("User login date updated successfully");
    }
}
