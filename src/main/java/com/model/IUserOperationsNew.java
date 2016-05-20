package com.model;

import java.io.IOException;

public interface IUserOperationsNew {

    /**
     * Search users by user name
     * Additional search params are here https://vk.com/dev/users.search
     *
     * @param name User name
     *
     * @return {@see IUserOperations#searchUsersByName}
     * @throws IOException
     */
    String searchUsersByName(String name) throws IOException;

    /**
     * Support multiple user_ids
     *
     * @param id User id
     * @return {@see IUserOperations#getPersonalInfoById}
     * @throws IOException
     */
    String getPersonalInfoById(String id) throws IOException;
}
