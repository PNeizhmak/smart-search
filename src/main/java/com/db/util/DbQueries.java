package com.db.util;

/**
 * @author Pavel Neizhmak
 */
public final class DbQueries {

    public static final String USERS_INSERT = "INSERT INTO `user_db`.`users` (`username`,`last_login_ts`,`user_created_ts`,`account_status_id`) VALUES (?,?,?,?)";
    public static final String USER_GET = "SELECT * FROM users u WHERE u.id = ?";

    private DbQueries() {
    }
}
