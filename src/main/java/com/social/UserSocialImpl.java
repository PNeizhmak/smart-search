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
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private Map<String, List<String>> colorDefinition;

    @Override
    public void storeUserSocialId(String socialNetwork, Long socialNetworkUserId) {
        final User user = (User) httpSession.getAttribute("user");

        userDao.storeUserSocialId(user.getId(), socialNetwork, socialNetworkUserId);
    }

    @Override
    public String checkDominantColor(String imageUrl) throws IOException {

        final String dominantColor = ImageUtils.getDominantColorByPhoto(new URL(imageUrl));

        final String baseColor = colorDefinition.entrySet().stream()
                .filter(e -> e.getValue().contains(dominantColor))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(dominantColor, "base color is: " + baseColor);

        return jsonObject.toString();
    }
}
