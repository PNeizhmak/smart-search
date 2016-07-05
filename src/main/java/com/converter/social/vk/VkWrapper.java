package com.converter.social.vk;

import com.google.gson.Gson;
import com.converter.model.VkUserInfoDto;
import com.converter.IConvertAPI;

/**
 * @author Pavel Neizhmak
 */
public class VkWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {

        Gson gson = new Gson();

        final VkUserInfoDto userInfoDto = gson.fromJson(response, VkUserInfoDto.class);

        return userInfoDto.getResponse().get(0).toString();
    }
}
