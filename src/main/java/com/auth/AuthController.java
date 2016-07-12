package com.auth;

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

    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody(required = true) final String username,
                          @RequestBody(required = true) final String password) throws Exception {

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
