package com.vk;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Pavel Neizhmak
 */
public class Vk {

    private static final String CLIENT_ID = "5087523";
    private static final String SCOPE = "offline";
    private static final String REDIRECT_URI = "http://oauth.vk.com/blank.html";
    private static final String VK_PREFIX = "https://api.vk.com/method/";
    private static final String DISPLAY = "page";
    private static final String RESPONSE_TYPE = "token";
    private static final String APP_SECRET = "0w2LtEeW1KWvtcCIRusx";

    private static final String ACCESS_TOKEN = "25a3b88adc5c8321385d9e5433a9f2880dc89df0eac66805fd38ccabcc9c8b29c9b392e45855ed61b4bd1";

    public static void main(String[] args) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;

            response = search(httpClient);
            final String searchStringResponse = EntityUtils.toString(response.getEntity());
            System.out.println(searchStringResponse);

            response = getPersonalInfoByUid(httpClient);
            String getInfoStringResponse = EntityUtils.toString(response.getEntity());
            System.out.println(getInfoStringResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search users by user name
     * Additional search params are here https://vk.com/dev/users.search
     *
     * @param httpClient {@see HttpClient}
     * @return {@see HttpResponse}
     * @throws IOException
     */
    private static HttpResponse search(HttpClient httpClient) throws IOException {
        HttpResponse response;
        HttpPost searchPost = new HttpPost(VK_PREFIX +
                "users.search" +
                "?q=Durov" +
                "&access_token=" + ACCESS_TOKEN);

        response = httpClient.execute(searchPost);
        searchPost.abort();
        return response;
    }

    /**
     * Support multiple user_ids
     *
     * @param httpClient {@see HttpClient}
     * @return {@see HttpResponse}
     * @throws IOException
     */
    private static HttpResponse getPersonalInfoByUid(HttpClient httpClient) throws IOException {
        HttpResponse response;
        HttpPost getInfoPost = new HttpPost(VK_PREFIX +
                "users.get" +
                "?user_ids=durov" +
                "&fields=city,contacts,site,education,status,connections" +
                "&name_case=Nom");

        response = httpClient.execute(getInfoPost);
        getInfoPost.abort();
        return response;
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
