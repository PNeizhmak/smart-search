package com.social.github;

import com.google.inject.Inject;
import com.model.IUserOperations;
import com.util.Constants;
import com.util.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

/**
 * @author Pavel Neizhmak
 */
@Path("github")
public class Github implements IUserOperations{

    private static final String CLIENT_ID = "36925ab7abc42e480b5c";
    private static final String CLIENT_SECRET = "356b6501a0e0e52bf5ff69ec90cbd46380a97ebf";
    private static final String REDIRECT_URI = "http://localhost";

    private static final String GITHUB_PREFIX = "api.github.com";

    private static final String TEMP_ACCESS_TOKEN = "f1f3bdd7ad8a4538d013af06b2b6dea6ca0f4254";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public String searchByName(@PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("access_token", TEMP_ACCESS_TOKEN));
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair("type", "Users"));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, GITHUB_PREFIX, "/search/users", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
    }

    @Override
    public String getUserInfo(String id) throws IOException, URISyntaxException {
        return null;
    }

    /**
     * Internal method to get access token for vk
     * Need to be executed only once
     * Flow: copy built request in browser, execute and get token
     * Temp solution to avoid redundant code
     *
     * @throws IOException
     */
    public void getAccessToken() throws IOException {
        HttpResponse response;

        HttpGet request = new HttpGet("https://github.com/login/oauth/authorize?" +
                "client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI);


        response = httpClient.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}