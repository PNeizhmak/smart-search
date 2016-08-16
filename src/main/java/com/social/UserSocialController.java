package com.social;

import com.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/user-social")
public class UserSocialController {

    @Autowired
    private IUserSocial userSocial;

    @RequestMapping(value = "/storeSocialIds", method = RequestMethod.POST, produces = Constants.APP_JSON_UTF_8)
    public void storeSocialIds(@RequestParam final String socialNetwork, @RequestParam final String socialNetworkUserId) {

        userSocial.storeUserSocialId(socialNetwork, Long.valueOf(socialNetworkUserId));
    }

    @RequestMapping(value = "/checkDominantColor", method = RequestMethod.POST, produces = Constants.APP_JSON_UTF_8)
    public String checkDominantColor(@RequestParam final String photoUrl) throws IOException {

        return userSocial.checkDominantColor(photoUrl);
    }
}
