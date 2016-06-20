package com.converter.google;

import com.converter.IConvertAPI;
import com.google.gson.Gson;
import com.model.GoogleUserInfoDto;

/**
 * @author Pavel Neizhmak
 */
public class GoogleWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {
        Gson gson = new Gson();

        final GoogleUserInfoDto userInfoDto = gson.fromJson(response, GoogleUserInfoDto.class);

        return userInfoDto.toString();
    }
}
