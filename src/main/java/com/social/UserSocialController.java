package com.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/user-social")
public class UserSocialController {

    @Autowired
    private IUserSocial userSocial;

    @RequestMapping(value = "/storeSocialIds", method = RequestMethod.POST, produces = "application/json")
    public void storeSocialIds(@RequestParam final String socialNetwork, @RequestParam final String socialNetworkUserId) {

        userSocial.storeUserSocialId(socialNetwork, Long.valueOf(socialNetworkUserId));
    }
}
