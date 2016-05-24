package com.converter.vk;

import com.google.gson.Gson;
import com.model.VkUserInfoDto;
import com.converter.IConvertAPI;

import java.util.Arrays;

/**
 * @author Pavel Neizhmak
 */
public class VkWrapper implements IConvertAPI {

    @Override
    public String parseUserInfo(String response) {

        Gson gson = new Gson();

        final VkUserInfoDto vkDto = gson.fromJson(response, VkUserInfoDto.class);

        return vkDto.getResponse().get(0).toString();
    }
}
