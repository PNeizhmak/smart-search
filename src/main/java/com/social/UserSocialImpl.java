package com.social;

import com.db.dao.IUserDao;
import com.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author Pavel Neizhmak
 */
@Component
public class UserSocialImpl implements IUserSocial {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private HttpSession httpSession;

    @Override
    public void storeUserSocialId(String socialNetwork, Long socialNetworkUserId) {
        final User user = (User) httpSession.getAttribute("user");

        userDao.storeUserSocialId(user.getId(), socialNetwork, socialNetworkUserId);
    }
}
