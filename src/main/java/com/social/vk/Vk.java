package com.social.vk;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.model.AccessTokenResponseBean;
import com.model.IUserOperations;
import com.social.exception.SmartSearchException;
import com.util.Constants;
import com.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_HTML;

@Path("vk")
public class Vk implements IUserOperations {

    private static final String CLIENT_ID = "5087523";
    private static final String REDIRECT_URI = "http://localhost:8081/rest/vk/setAccessToken";
    private static final String VK_PREFIX = "api.vk.com/method";
    private static final String APP_SECRET = "0w2LtEeW1KWvtcCIRusx";

    private static final String AUTOCLOSE_HTML_WINDOW_MARKUP = "<script type=\"text/javascript\">window.opener.postMessage(window.document.cookie, " +
            "'http://localhost:8081');" +
            "setTimeout('window.close()', 1000);</script>";

    private static Map<String, AccessTokenResponseBean> accessTokensMap = new ConcurrentHashMap<>();

    @Inject
    private HttpClient httpClient;

    @Inject
    private Gson gson;

    @GET
    @Path("/{userId}/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public Response searchByName(@PathParam("userId") final String userId, @PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        AccessTokenResponseBean token = accessTokensMap.get(userId);
        if (token == null || StringUtils.isEmpty(token.access_token)) {
            throw new SmartSearchException("No token.");
        }
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token.access_token));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, VK_PREFIX, "/users.search", nameValuePairs);
        HttpPost searchPost = new HttpPost(uri);

        response = httpClient.execute(searchPost);
        searchPost.abort();

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

    @GET
    @Path("/{userId}/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public Response getUserInfo(@PathParam("userId") final String userId, @PathParam("id") final String id, @MatrixParam("params") final List<String> jsonParamsMap)
            throws IOException, URISyntaxException {
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

    @GET
    @Path("/setAccessToken")
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_HTML)
    public Response setAccessToken(@QueryParam("code") final String code) {
        Response response = Response.ok().build();
        try {
            if (code != null) {
                System.out.println("Code: " + code);
                AccessTokenResponseBean token = getAccessToken(code);
                accessTokensMap.put(String.valueOf(token.user_id), token);
                NewCookie cookie = new NewCookie("user_id", String.valueOf(token.user_id));
                response = Response.ok(AUTOCLOSE_HTML_WINDOW_MARKUP).cookie(cookie).build();
            }
        } catch (IOException e) {
            response = Response.serverError().entity(e.getMessage()).build();
        } finally {
            return response;
        }
    }

    private AccessTokenResponseBean getAccessToken(final String code) throws IOException {
        HttpGet request = new HttpGet("https://oauth.vk.com/access_token?" +
                                                "client_id=" + CLIENT_ID +
                                                "&client_secret=" + APP_SECRET +
                                                "&redirect_uri=" + REDIRECT_URI +
                                                "&code=" + code);
        HttpResponse response = httpClient.execute(request);
        String responseJson = EntityUtils.toString(response.getEntity());
        System.out.println(responseJson);
        return gson.fromJson(responseJson, AccessTokenResponseBean.class);
    }
}
