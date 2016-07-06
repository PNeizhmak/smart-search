package com.db;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.util.UriUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Pavel Neizhmak
 */
public class TestConnection {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("db-config.xml");
        final long testUserId = 5L;
        final String testUsername = "testUser";

        IUserDao userDao = ctx.getBean("userDao", IUserDao.class);

        userDao.createNewUser(testUsername, "hash", null);
        System.out.println("User creation done !");

        User user2read = userDao.getByName(testUsername);
        System.out.println("User retrieved: " + user2read);

        String hashPass = userDao.getPassword(testUserId);
        String strPass = UriUtils.decryptPassword(hashPass);
        System.out.println("Hash pass is: " + hashPass);
        System.out.println("String pass is: " + strPass);

        userDao.updateLoginTs(testUserId);
        System.out.println("User login date updated successfully");
    }
}
