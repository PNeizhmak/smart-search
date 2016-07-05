package com.converter.social.instagram;

import com.converter.IConvertAPI;
import com.google.gson.Gson;
import com.converter.model.InstagramUserInfoDto;

/**
 * @author Pavel Neizhmak
 */
public class InstagramWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {

        Gson gson = new Gson();

        final InstagramUserInfoDto userInfoDto = gson.fromJson(response, InstagramUserInfoDto.class);

        return userInfoDto.toString();
    }
}
