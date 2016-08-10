package com.social.vk;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.social.exception.SmartSearchException;
import com.util.Constants;
import com.util.UriUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/vk")
public class Vk {

    private static final String VK_PREFIX = "api.vk.com/method";

    @Autowired
    private HttpClient httpClient;

    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity searchByName(@RequestParam("token") final String token,
                                       @PathVariable("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        if (token == null || StringUtils.isEmpty(token)) {
            throw new SmartSearchException("No token.");
        }

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair("fields", "photo,screen_name"));
        nameValuePairs.add(new BasicNameValuePair("count", "1000"));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, VK_PREFIX, "/users.search", nameValuePairs);
        HttpPost searchPost = new HttpPost(uri);

        response = httpClient.execute(searchPost);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        searchPost.abort();

        return ResponseEntity.ok(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity getUserInfo(@PathVariable("id") final String id) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("user_ids", id));
        nameValuePairs.add(new BasicNameValuePair("fields", "city,contacts,site,education,status,connections,photo_medium,photo_big"));
        nameValuePairs.add(new BasicNameValuePair("name_case", "Nom"));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, VK_PREFIX, "/users.get", nameValuePairs);
        HttpPost getInfoPost = new HttpPost(uri);

        response = httpClient.execute(getInfoPost);
        getInfoPost.abort();

        String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

}
