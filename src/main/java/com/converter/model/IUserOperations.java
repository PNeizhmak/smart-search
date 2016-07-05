package com.converter.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.Response;

public interface IUserOperations {

    Response searchByName(String userId, String name) throws IOException, URISyntaxException;

    Response getUserInfo(String userId, String id, List<String> jsonParamsMap) throws IOException, URISyntaxException;
}
