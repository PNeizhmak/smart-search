package com.model;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IUserOperations {

    String searchByName(String name) throws IOException, URISyntaxException;

    String getUserInfo(String id) throws IOException, URISyntaxException;
}
