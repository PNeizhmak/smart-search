package com.converter.social.github;

import com.converter.IConvertAPI;
import com.google.gson.Gson;
import com.converter.model.GithubUserInfoDto;

/**
 * @author Pavel Neizhmak
 */
public class GithubWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {

        Gson gson = new Gson();

        final GithubUserInfoDto userInfoDto = gson.fromJson(response, GithubUserInfoDto.class);

        return userInfoDto.toString();
    }
}
