package com.social.google;

import com.google.inject.Inject;
import com.converter.model.IUserOperations;
import com.util.Constants;
import com.util.UriUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 *         <p>
 *         At this moment "plus.people.search" and "plus.people.get" methods work without OAuth
 *         use PROJECT_ID and CLIENT_ID to authenticate
 */
@Path("googlePlus")
public class GooglePlus implements IUserOperations {

    private static final String SERVER_KEY = "AIzaSyCi3ydaT4VHxTPLBwrI08ETfUISsHFcE88";
    private static final String PROJECT_ID = "smart-search-1344";
    private static final String CLIENT_ID = "104617963921-rjsaj5ssd01i2g9tek2ugidrdp4uhn54.apps.googleusercontent.com";
    private static final String PLUS_PREFIX = "www.googleapis.com/plus/v1";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/{userId}/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public Response searchByName(@PathParam("userId") final String userId, @PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("query", name));
        nameValuePairs.add(new BasicNameValuePair("key", SERVER_KEY));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, PLUS_PREFIX, "/people", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }

    @GET
    @Path("/{userId}/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public Response getUserInfo(@PathParam("userId") final String userId,
                                @PathParam("id") final String id,
                                @MatrixParam("params") final List<String>
            jsonParamsMap)
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
