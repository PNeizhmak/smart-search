package com.auth.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

/**
 * @author Pavel Neizhmak
 */
public interface IAuthService {

    Response login(String username, String password) throws Exception;

    Response register(String username, String password, String email);
}
