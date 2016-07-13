package com.auth.services;

import org.springframework.http.ResponseEntity;

/**
 * @author Pavel Neizhmak
 */
public interface IAuthService {

    ResponseEntity login(String username, String password) throws Exception;

    ResponseEntity register(String username, String password, String email);
}
