package com.facebook;

import com.model.IUserOperations;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Pavel Neizhmak
 */
public class Facebook implements IUserOperations {

    private static final String CLIENT_ID = "1092057530837278";
    private static final String REDIRECT_URI = "http://localhost/";
    private static final String CLIENT_SECRET = "82f17781434698524c40568362cfde36";
    private static final String FB_GRAPH_FREFIX = "https://graph.facebook.com/";

    private static final String CODE = "AQAUjaGEEQ9dxkhr1CJ6IdvwcVee4InTph7xaNgKST08B4BIaz-FCh2thI3TQlpUNzLtC78b1_awWRmVcJtCFsAVHuoM7YP_o8Zdxuz7YrQ_vCggApBD_26VEU43UQH7AK6Azx0pmQNq-kN38XYSfirGAZzVxoSHAwTmaj3305Cc4DRT7xIQf-yZppb1OQLq4eDY2rizhnpG4zhTttHnDaQYucIZVnK9ca8O3egtr97C87632qerK3nJh5dHHtF5sPpOi0Ajb4-GubTALNn3_wAJAKnD-06DdNT7G3E_DcL1KYDpF3l9DmqMXK8VjUUlovowmcPomkKwuPLeYS1tt5iX";
    private static String TEMP_ACCESS_TOKEN = "EAAPhOHUzKR4BAAepbKnaXz2WevvLJHrjyHqL2eKkT7vUTYkGaLVrGDgIBwmbOQ4t4DhlZAKzrCQ1XNrWvtZBbfkZCaSPxMvVY2nwxrMhtYQh2fjNbaRcZAZC6k5bllSmC2YVYV9QY4Wm16H9TjEvOZCgEKZC2ZBm7k4ZD";

    /**
     * Native search method supports these types of objects:
     * All public posts (post), people (user), pages (page), events (event), groups (group), check-ins (checkin)
     *
     * @param httpClient {@see HttpClient}
     * @return {@see IUserOperations#searchUsersByName}
     * @throws IOException
     */
    public String searchUsersByName(HttpClient httpClient) throws IOException {
        HttpResponse response;
        HttpGet searchRequest = new HttpGet(FB_GRAPH_FREFIX +
                "search" +
                "?access_token=" + TEMP_ACCESS_TOKEN +
                "&q=Zuckerberg" +
                "&type=user");

        response = httpClient.execute(searchRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(stringResponse);

        return stringResponse;
    }

    /**
     * Gets short personal info e.g link to page
     *
     * @param httpClient {@see HttpClient}
     * @return {@see IUserOperations#getPersonalInfoById}
     * @throws IOException
     */
    public String getPersonalInfoById(HttpClient httpClient) throws IOException {
        final String userIdToSearch = "4";
        HttpResponse response;

        HttpGet getInfoRequest = new HttpGet(FB_GRAPH_FREFIX +
                userIdToSearch +
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
