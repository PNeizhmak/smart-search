package com.social.vk;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.social.exception.SmartSearchException;
import com.util.Constants;
import com.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("vk")
public class Vk {

    private static final String VK_PREFIX = "api.vk.com/method";

    @Inject
    private HttpClient httpClient;

    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public Response searchByName(@QueryParam("token") final String token, @PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        if (token == null || StringUtils.isEmpty(token)) {
            throw new SmartSearchException("No token.");
        }
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));

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
    public Response getUserInfo(@PathParam("id") final String id) throws IOException, URISyntaxException {
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

        return Utils.buildResponse(stringResponse);
    }

}
