package com.util;

import com.converter.IConvertAPI;
import com.converter.JsonConverter;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Pavel Neizhmak
 */
public class Utils {

    public static URI buildRequest(final String schema, final String host, final String path, List<NameValuePair> params) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(schema)
                .setHost(host)
                .setPath(path)
                .setParameters(params)
                .build();
    }

    /**
     * Builds JSON response
     *
     * @param stringResponse stringResponse
     * @return json response representation
     */
    public static String buildResponse(final String stringResponse) {
        System.out.println(stringResponse);
        return stringResponse;
    }

    /**
     * Builds POJO response
     *
     * @param stringResponse stringResponse
     * @param convertAPI     {@see IConvertAPI}
     * @return pojo response representation
     */
    public static String buildResponse(final String stringResponse, final IConvertAPI convertAPI) {
        JsonConverter jsonConverter = new JsonConverter(convertAPI);
        final String parsedData = jsonConverter.parseUserInfo(stringResponse);

        System.out.println(parsedData);
        return parsedData;
    }

}
