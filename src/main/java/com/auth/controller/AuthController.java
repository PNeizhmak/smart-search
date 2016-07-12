package com.auth.controller;

import com.auth.model.AuthDetails;
import com.auth.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

/**
 * @author Pavel Neizhmak
 */
@Controller
@RequestMapping("/rest/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody(required = true) final AuthDetails authDetails) throws Exception {

        final String username = authDetails.getUsername();
        final String password = authDetails.getPassword();

        return authService.login(username, password);
    }

    public Response register(@FormParam("username") final String username, @FormParam("password") final String password,
                             @FormParam("email") final String email) throws Exception {

        return authService.register(username, password, email);
    }

    public void setAuthService(IAuthService authService) {
        this.authService = authService;
    }
}
