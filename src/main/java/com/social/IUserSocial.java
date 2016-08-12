package com.social;

import java.io.IOException;

/**
 * @author Pavel Neizhmak
 */
public interface IUserSocial {

    void storeUserSocialId(String socialNetwork, Long socialNetworkUserId);

    String checkDominantColor(String imageUrl) throws IOException;
}
