package com.auth;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.util.PasswordUtils;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 */
@Path("auth")
public class AuthController {

    @Inject
    private IUserDao userDao;

    @Inject
    private Gson gson;

    @POST
    @Path("/login")
    @Produces(APPLICATION_JSON)
    public Response login(@FormParam("username") final String username, @FormParam("password") final String password) throws Exception {

        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            final String dbPass = userDao.getPassword(dbUser.getId());
            final String userPass = PasswordUtils.encryptPassword(password);
            if (dbPass.equals(userPass)) {
                success();
            } else {
                return passwordIncorrect();
            }
        }
        return usernameNotFound();
    }

    private Response passwordIncorrect() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Password is incorrect");
        return Response.ok(gson.toJson(innerObject)).build();
    }

    private Response success() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("success", "Success");
        return Response.ok(gson.toJson(innerObject)).build();
    }

    private Response usernameNotFound() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Username is not found");
        return Response.ok(gson.toJson(innerObject)).build();
    }
}
