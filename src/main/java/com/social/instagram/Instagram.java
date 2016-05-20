package com.social.instagram;

import com.google.inject.Inject;
import com.model.IUserOperations;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;

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
    private static final String INSTAGRAM_PREFIX = "https://api.instagram.com/";
    private static final String API_VERSION = "v1";
    private static final String CLIENT_SECRET = "feb99c46f2234f0580ac167e01c47c5f";

    private static final String TEMP_ACCESS_TOKEN = "185507778.1fb234f.c62a8528849b4388a184edd97aa74993";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public String searchByName(@PathParam("name") final String name) throws IOException {

        HttpResponse response;
        HttpGet searchGet = new HttpGet(INSTAGRAM_PREFIX +
                API_VERSION + "/users/search" +
                "?q=" + name +
                "&access_token=" + TEMP_ACCESS_TOKEN);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") final String id) throws IOException {
        HttpResponse response;

        HttpGet getInfoGet = new HttpGet(INSTAGRAM_PREFIX +
                API_VERSION + "/users/" +
                id +
                "?access_token=" + TEMP_ACCESS_TOKEN);

        response = httpClient.execute(getInfoGet);

        String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
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
