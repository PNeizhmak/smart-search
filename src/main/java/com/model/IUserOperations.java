package com.model;

import org.apache.http.client.HttpClient;

import java.io.IOException;

/**
 * @author Pavel Neizhmak
 */
public interface IUserOperations {

    /**
     * searches users by full or partial name
     *
     * @param httpClient {@see HttpClient}
     * @return json represented as a string
     * @throws IOException
     */
    String searchUsersByName(HttpClient httpClient) throws IOException;

    /**
     * gets detailed user info
     *
     * @param httpClient {@see HttpClient}
     * @return json represented as a string
     * @throws IOException
     */
    String getPersonalInfoById(HttpClient httpClient) throws IOException;
}
