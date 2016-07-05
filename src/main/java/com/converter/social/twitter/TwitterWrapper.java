package com.converter.social.twitter;

import com.converter.IConvertAPI;
import com.google.gson.Gson;
import com.converter.model.TwitterUserInfoDto;

/**
 * @author Pavel Neizhmak
 */
public class TwitterWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {

        Gson gson = new Gson();

        final TwitterUserInfoDto twitterUserInfoDto = gson.fromJson(response, TwitterUserInfoDto.class);

        return twitterUserInfoDto.toString();
    }
}
