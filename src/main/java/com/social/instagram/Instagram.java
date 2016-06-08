package com.social.instagram;

import com.converter.instagram.InstagramWrapper;
import com.google.inject.Inject;
import com.model.IUserOperations;
import com.util.Constants;
import com.util.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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
@Path("instagram")
public class Instagram implements IUserOperations {

    private static final String CLIENT_ID = "d350fe69884746199db9eec8f9a8048a";
    private static final String SCOPE = "basic+public_content+comments+relationships+likes+follower_list";
    private static final String RESPONSE_TYPE = "token";
    private static final String REDIRECT_URI = "http://localhost";
    private static final String INSTAGRAM_PREFIX = "api.instagram.com/v1";
    private static final String CLIENT_SECRET = "feb99c46f2234f0580ac167e01c47c5f";

    private static final String TEMP_ACCESS_TOKEN = "185507778.1fb234f.c62a8528849b4388a184edd97aa74993";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public String searchByName(@PathParam("name") final String name) throws IOException, URISyntaxException {

        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, TEMP_ACCESS_TOKEN));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, INSTAGRAM_PREFIX, "/users/search", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") final String id, @MatrixParam("params") final List<String> jsonParamsMap)
            throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, TEMP_ACCESS_TOKEN));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, INSTAGRAM_PREFIX, "/users/" + id, nameValuePairs);
        HttpGet getInfoGet = new HttpGet(uri);

        response = httpClient.execute(getInfoGet);

        String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse, new InstagramWrapper());
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
