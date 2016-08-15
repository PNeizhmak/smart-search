package com.social;

import com.db.dao.IUserDao;
import com.db.model.User;
import com.util.ImageUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel Neizhmak
 */
@Component
public class UserSocialImpl implements IUserSocial {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private Map<String, List<String>> colorDefinition;

    @Override
    public void storeUserSocialId(String socialNetwork, Long socialNetworkUserId) {
        final User user = (User) httpSession.getAttribute("user");

        userDao.storeUserSocialId(user.getId(), socialNetwork, socialNetworkUserId);
    }

    @Override
    public String checkDominantColor(String imageUrl) throws IOException {

        final String dominantColor = ImageUtils.getDominantColorByPhoto(new URL(imageUrl));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(dominantColor,"description will be here");

        return jsonObject.toString();
    }
}
