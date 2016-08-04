package com.social.instagram;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 *         <p>
 *         Current problem is that smart-search app must be in live mode to provide global search
 *         To submit the app video screencast URL must be provided
 *         <p>
 *         solution1: submit my app
 *         solution2: use 3d party live app provides search
 *         <p>
 *         current solution: i've generated the token via https://apigee.com/console
 */
@RestController
@RequestMapping("/rest/instagram")
public class Instagram {

    private static final String CLIENT_ID = "d350fe69884746199db9eec8f9a8048a";
    private static final String SCOPE = "basic+public_content+comments+relationships+likes+follower_list";
    private static final String RESPONSE_TYPE = "token";
    private static final String REDIRECT_URI = "http://localhost";
    private static final String INSTAGRAM_PREFIX = "api.instagram.com/v1";
    private static final String CLIENT_SECRET = "feb99c46f2234f0580ac167e01c47c5f";

    private static final String TEMP_ACCESS_TOKEN = "185507778.1fb234f.c62a8528849b4388a184edd97aa74993";

    @Autowired
    private HttpClient httpClient;

    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public Response searchByName(@PathVariable("name") final String name) throws IOException, URISyntaxException {

        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, TEMP_ACCESS_TOKEN));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, INSTAGRAM_PREFIX, "/users/search", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public Response getUserInfo(@PathVariable("id") final String id, @MatrixVariable(value = "params", required = false) final List<String> jsonParamsMap)
            throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, TEMP_ACCESS_TOKEN));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, INSTAGRAM_PREFIX, "/users/" + id, nameValuePairs);
        HttpGet getInfoGet = new HttpGet(uri);

        response = httpClient.execute(getInfoGet);

        String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }

    private static void getAccessToken() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;

        HttpPost auth1 = new HttpPost(INSTAGRAM_PREFIX +
                "oauth/authorize/?" +
                "client_id=" + CLIENT_ID +
                "&scope=" + SCOPE +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=" + RESPONSE_TYPE);


        response = httpClient.execute(auth1);
        auth1.abort();
        System.out.println(response);
    }
}
