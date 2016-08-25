package com.social.google;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.util.Constants;
import com.util.UriUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 *         <p>
 *         At this moment "plus.people.search" and "plus.people.get" methods work without OAuth
 *         use PROJECT_ID and CLIENT_ID to authenticate
 */
@RestController
@RequestMapping("/rest/googlePlus")
public class GooglePlus {

    private static final String SERVER_KEY = "AIzaSyCi3ydaT4VHxTPLBwrI08ETfUISsHFcE88";
    private static final String PROJECT_ID = "smart-search-1344";
    private static final String CLIENT_ID = "104617963921-rjsaj5ssd01i2g9tek2ugidrdp4uhn54.apps.googleusercontent.com";
    private static final String PLUS_PREFIX = "www.googleapis.com/plus/v1";

    @Autowired
    private HttpClient httpClient;

    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity searchByName(@PathVariable("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("query", name));
        nameValuePairs.add(new BasicNameValuePair("key", SERVER_KEY));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, PLUS_PREFIX, "/people", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public Response getUserInfo(@PathVariable("id") final String id, @MatrixVariable(value = "params", required = false) final List<String> jsonParamsMap)
        throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("key", SERVER_KEY));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, PLUS_PREFIX, "/people/" + id, nameValuePairs);
        HttpGet getUserInfo = new HttpGet(uri);

        response = httpClient.execute(getUserInfo);

        String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }
}
