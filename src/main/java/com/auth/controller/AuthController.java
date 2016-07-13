package com.auth.controller;

import com.auth.model.AuthDetails;
import com.auth.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity login(@RequestBody final AuthDetails authDetails) throws Exception {

        final String username = authDetails.getUsername();
        final String password = authDetails.getPassword();

        return authService.login(username, password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity register(@RequestBody final AuthDetails authDetails) throws Exception {

        final String username = authDetails.getUsername();
        final String password = authDetails.getPassword();
        final String email = authDetails.getEmail();

        return authService.register(username, password, email);
    }

}
