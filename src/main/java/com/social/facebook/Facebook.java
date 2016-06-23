package com.social.facebook;

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
import com.util.Constants;
import com.util.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 */
@Path("fb")
public class Facebook {

    private static final String FB_GRAPH_FREFIX = "graph.facebook.com";

    @Inject
    private HttpClient httpClient;

    /**
     * Native search method supports these types of objects:
     * All public posts (post), people (user), pages (page), events (event), groups (group), check-ins (checkin)
     *
     * @return {@see IUserOperations#searchByName}
     * @throws IOException
     */
    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public Response searchByName(@QueryParam("token") final String token, @PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair("type", "user"));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, FB_GRAPH_FREFIX, "/search", nameValuePairs);
        HttpGet searchRequest = new HttpGet(uri);

        response = httpClient.execute(searchRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public Response getUserInfo(@QueryParam("token") final String token, @PathParam("id") final String id)
            throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("fields", "id,name,picture"));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, FB_GRAPH_FREFIX, "/" + id, nameValuePairs);
        HttpGet getInfoRequest = new HttpGet(uri);

        response = httpClient.execute(getInfoRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

}
