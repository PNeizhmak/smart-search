package com.auth;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 */
public class AuthController {

    @Autowired
    private IUserDao userDao;

    @POST
    @Path("/login")
    @Produces(APPLICATION_JSON)
    public String login(@FormParam("username") final String username, @FormParam("password") final String password) throws Exception {

        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            final String dbPass = userDao.getPassword(dbUser.getId());
            final String userPass = PasswordUtils.encryptPassword(password);
            if (dbPass.equals(userPass)) {
                return "{success: 'Success'}";
            } else {
                return "{error: 'Password is incorrect'}";
            }
        }
        return "{error: 'Username is not found'}";
    }
}
