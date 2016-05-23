package com.util;

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

}
