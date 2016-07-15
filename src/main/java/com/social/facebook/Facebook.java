package com.social.facebook;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.util.Constants;
import com.util.UriUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
@RequestMapping("/rest/fb")
public class Facebook {

    private static final String FB_GRAPH_FREFIX = "graph.facebook.com";

    @Autowired
    private HttpClient httpClient;

    /**
     * Native search method supports these types of objects:
     * All public posts (post), people (user), pages (page), events (event), groups (group), check-ins (checkin)
     *
     * @return {@see IUserOperations#searchByName}
     * @throws IOException
     */
    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity searchByName(@RequestParam("token") final String token,
                                 @PathVariable("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));
        nameValuePairs.add(new BasicNameValuePair("q", name));
        nameValuePairs.add(new BasicNameValuePair("type", "user"));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, FB_GRAPH_FREFIX, "/search", nameValuePairs);
        HttpGet searchRequest = new HttpGet(uri);

        response = httpClient.execute(searchRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity getUserInfo(@RequestParam("token") final String token, @PathVariable("id") final String id)
            throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("fields", "id,name,picture"));
        nameValuePairs.add(new BasicNameValuePair(Constants.ACCESS_TOKEN, token));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, FB_GRAPH_FREFIX, "/" + id, nameValuePairs);
        HttpGet getInfoRequest = new HttpGet(uri);

        response = httpClient.execute(getInfoRequest);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

}
