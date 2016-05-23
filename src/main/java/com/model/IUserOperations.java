package com.model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IUserOperations {

    /**
     * Search users by user name
     * Additional search params are here https://vk.com/dev/users.search
     *
     * @param name User name
     *
     * @return {@see IUserOperations#searchByName}
     * @throws IOException
     */
    String searchByName(String name) throws IOException, URISyntaxException;

    /**
     * Support multiple user_ids
     *
     * @param id User id
     * @return {@see IUserOperations#getUserInfo}
     * @throws IOException
     */
    String getUserInfo(String id) throws IOException, URISyntaxException;
}
