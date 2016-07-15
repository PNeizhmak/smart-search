package com.auth.services;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author Pavel Neizhmak
 */
@Component
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private HttpSession httpSession;

    private Gson gson = new Gson();

    @Override
    public ResponseEntity login(String username, String password) throws Exception {
        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            final String dbPass = userDao.getPassword(dbUser.getId());
            final String userPass = PasswordUtils.encryptPassword(password);
            if (dbPass.equals(userPass)) {
                httpSession.setAttribute("user", dbUser);
                return success();
            } else {
                return passwordIncorrect();
            }
        }
        return usernameNotFound();
    }

    @Override
    public ResponseEntity register(String username, String password, String email) {
        final User dbUser = userDao.getByName(username);
        if (dbUser != null) {
            return duplicatedUsername();
        } else {
            userDao.createNewUser(username, password, email);
        }
        return success();
    }

    private ResponseEntity passwordIncorrect() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Password is incorrect");
        return ResponseEntity.ok(gson.toJson(innerObject));
    }

    private ResponseEntity success() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("success", "Success");
        return ResponseEntity.ok(gson.toJson(innerObject));
    }

    private ResponseEntity usernameNotFound() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Username is not found");
        return ResponseEntity.ok(gson.toJson(innerObject));
    }

    private ResponseEntity duplicatedUsername() {
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("error", "Duplicated username");
        return ResponseEntity.ok(gson.toJson(innerObject));
    }

}
