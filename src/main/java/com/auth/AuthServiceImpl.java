package com.auth;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.util.PasswordUtils;
import javax.ws.rs.core.Response;

/**
 * @author Pavel Neizhmak
 */
public class AuthServiceImpl implements IAuthService {

    private IUserDao userDao;

    private Gson gson = new Gson();

    @Override
    public Response login(String username, String password) throws Exception {
        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            final String dbPass = userDao.getPassword(dbUser.getId());
            final String userPass = PasswordUtils.encryptPassword(password);
            if (dbPass.equals(userPass)) {
                return success();
            } else {
                return passwordIncorrect();
            }
        }
        return usernameNotFound();
    }

    @Override
    public Response register(String username, String password, String email) {
        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            return duplicatedUsername();
        } else {
            userDao.createNewUser(username, password, email);
        }
        return success();
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

    private Response duplicatedUsername() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Duplicated username");
        return Response.ok(gson.toJson(innerObject)).build();
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
