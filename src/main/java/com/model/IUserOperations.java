package com.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IUserOperations {

    String searchByName(String userId, String name) throws IOException, URISyntaxException;

    String getUserInfo(String userId, String id, List<String> jsonParamsMap) throws IOException, URISyntaxException;
}
