package com.db.util;

/**
 * @author Pavel Neizhmak
 */
public final class DbQueries {

    public static final String USER_INSERT = "INSERT INTO `user_db`.`users` (`username`, `email`, `last_login_ts`,`user_created_ts`,`account_status_id`) VALUES (?,?,?,?,?)";
    public static final String USER_GET = "SELECT * FROM users u WHERE u.username = ?";
    public static final String USER_UPDATE_LAST_LOGIN_TS = "UPDATE `user_db`.`users` SET `last_login_ts` = ? WHERE `id` = ?";
    public static final String USER_INSERT_PASSWORD = "INSERT INTO `user_db`.`user_password` (`user_id`, `password`) VALUES (?, ?)";
    public static final String USER_GET_PASSWORD = "SELECT up.`password` FROM `user_password` up WHERE `user_id` = ?";
    public static final String USER_CHECK_SOCIAL_NETWORK_EXISTS = "SELECT * FROM `user_db`.`user_in_social_network` WHERE ss_user_id = ? and social_network_type = ?";
    public static final String USER_STORE_SOCIAL_NETWORK_ID = "INSERT INTO `user_db`.`user_in_social_network` (`ss_user_id`,`social_network_id`,`social_network_type`) VALUES (?,?,?)";

    private DbQueries() {
    }
}
