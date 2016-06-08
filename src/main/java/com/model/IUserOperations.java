package com.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IUserOperations {

    String searchByName(String name) throws IOException, URISyntaxException;

    String getUserInfo(String id, List<String> jsonParamsMap) throws IOException, URISyntaxException;
}
