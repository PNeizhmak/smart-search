package com.instagram;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

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
public class Instagram {

    private static final String CLIENT_ID = "d350fe69884746199db9eec8f9a8048a";
    private static final String SCOPE = "basic+public_content+comments+relationships+likes+follower_list";
    private static final String RESPONSE_TYPE = "token";
    private static final String REDIRECT_URI = "http://localhost";
    private static final String INSTAGRAM_PREFIX = "https://api.instagram.com/";
    private static final String CLIENT_SECRET = "feb99c46f2234f0580ac167e01c47c5f";

    private static final String TEMP_ACCESS_TOKEN = "185507778.1fb234f.c62a8528849b4388a184edd97aa74993";


    public static void main(String[] args) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;

            response = search(httpClient);
            final String searchStringResponse = EntityUtils.toString(response.getEntity());
            System.out.println(searchStringResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpResponse search(HttpClient httpClient) throws IOException {
        HttpResponse response;
        HttpGet searchGet = new HttpGet(INSTAGRAM_PREFIX +
                "v1/users/search" +
                "?q=Durov" +
                "&access_token=" + TEMP_ACCESS_TOKEN);

        response = httpClient.execute(searchGet);
        return response;
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
