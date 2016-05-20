package com.social.facebook;

import com.google.inject.Inject;
import com.model.IUserOperations;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URLEncoder;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 */
@Path("fb")
public class Facebook implements IUserOperations {

    private static final String CLIENT_ID = "1092057530837278";
    private static final String REDIRECT_URI = "http://localhost/";
    private static final String CLIENT_SECRET = "82f17781434698524c40568362cfde36";
    private static final String FB_GRAPH_FREFIX = "https://graph.facebook.com/";

    private static final String CODE = "AQAUjaGEEQ9dxkhr1CJ6IdvwcVee4InTph7xaNgKST08B4BIaz-FCh2thI3TQlpUNzLtC78b1_awWRmVcJtCFsAVHuoM7YP_o8Zdxuz7YrQ_vCggApBD_26VEU43UQH7AK6Azx0pmQNq-kN38XYSfirGAZzVxoSHAwTmaj3305Cc4DRT7xIQf-yZppb1OQLq4eDY2rizhnpG4zhTttHnDaQYucIZVnK9ca8O3egtr97C87632qerK3nJh5dHHtF5sPpOi0Ajb4-GubTALNn3_wAJAKnD-06DdNT7G3E_DcL1KYDpF3l9DmqMXK8VjUUlovowmcPomkKwuPLeYS1tt5iX";
    private static String TEMP_ACCESS_TOKEN = "EAAPhOHUzKR4BAAepbKnaXz2WevvLJHrjyHqL2eKkT7vUTYkGaLVrGDgIBwmbOQ4t4DhlZAKzrCQ1XNrWvtZBbfkZCaSPxMvVY2nwxrMhtYQh2fjNbaRcZAZC6k5bllSmC2YVYV9QY4Wm16H9TjEvOZCgEKZC2ZBm7k4ZD";

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
    public String searchByName(@PathParam("name") final String name) throws IOException {
        HttpResponse response;
        HttpGet searchRequest = new HttpGet(FB_GRAPH_FREFIX +
                "search" +
                "?access_token=" + TEMP_ACCESS_TOKEN +
                "&q=" + name +
                "&type=user");

        response = httpClient.execute(searchRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") final String id) throws IOException {
        HttpResponse response;

        HttpGet getInfoRequest = new HttpGet(FB_GRAPH_FREFIX +
                id +
                "?fields=id,name,picture" +
                "&access_token=" + TEMP_ACCESS_TOKEN);

        response = httpClient.execute(getInfoRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
    }

    public String getAccessToken() throws IOException {
        HttpResponse response;

        //url to get {code}
        HttpGet getCode = new HttpGet("http://www.facebook.com/dialog/oauth?" + "client_id="
                + CLIENT_ID + "&redirect_uri="
                + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                + "&scope=email");

        response = HttpClients.createDefault().execute(getCode);

        //request to get token
        HttpGet getToken = new HttpGet("https://graph.facebook.com/oauth/access_token?"
                + "client_id=" + CLIENT_ID + "&redirect_uri="
                + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                + "&client_secret=" + CLIENT_SECRET + "&code=" + CODE);

        response = HttpClients.createDefault().execute(getToken);

        return "";
    }
}
