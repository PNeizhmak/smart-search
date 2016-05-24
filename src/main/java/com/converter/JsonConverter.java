package com.converter;

/**
 * @author Pavel Neizhmak
 */
public class JsonConverter {

    private IConvertAPI convertAPI;

    public JsonConverter(IConvertAPI convertAPI) {
        this.convertAPI = convertAPI;
    }

    public String parseUserInfo(String response) {
        return convertAPI.parseUserInfo(response);
    }
}
