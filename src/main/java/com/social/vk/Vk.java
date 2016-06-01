package com.social.vk;

import com.google.inject.Inject;
import com.model.IUserOperations;
import com.converter.JsonConverter;
import com.converter.vk.VkWrapper;
import com.util.Constants;
import com.util.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("vk")
public class Vk implements IUserOperations {

    private static final String CLIENT_ID = "5087523";
    private static final String SCOPE = "offline";
    private static final String REDIRECT_URI = "http://oauth.vk.com/blank.html";
    private static final String VK_PREFIX = "api.vk.com/method";
    private static final String DISPLAY = "page";
    private static final String RESPONSE_TYPE = "token";
    private static final String APP_SECRET = "0w2LtEeW1KWvtcCIRusx";

    private static final String ACCESS_TOKEN = "25a3b88adc5c8321385d9e5433a9f2880dc89df0eac66805fd38ccabcc9c8b29c9b392e45855ed61b4bd1";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public String searchByName(@PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, ACCESS_TOKEN));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, VK_PREFIX, "/users.search", nameValuePairs);
        HttpPost searchPost = new HttpPost(uri);

        response = httpClient.execute(searchPost);
        searchPost.abort();

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") final String id) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("user_ids", id));
        nameValuePairs.add(new BasicNameValuePair("fields", "city,contacts,site,education,status,connections"));
        nameValuePairs.add(new BasicNameValuePair("name_case", "Nom"));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, VK_PREFIX, "/users.get", nameValuePairs);
        HttpPost getInfoPost = new HttpPost(uri);

        response = httpClient.execute(getInfoPost);
        getInfoPost.abort();

        String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse, new VkWrapper());
    }

    /**
     * Internal method to get access token for vk
     * Need to be executed only once
     * Flow: copy built request in browser, execute and get token
     * Temp solution to avoid redundant code
     *
     * @throws IOException
     */
    private static void getAccessToken() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;

        HttpPost request = new HttpPost("http://oauth.vk.com/authorize?" +
                "client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=" + SCOPE +
                "&display=" + DISPLAY +
                "&response_type=" + RESPONSE_TYPE);


        response = httpClient.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
