package com.social.twitter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.util.Constants;
import com.util.UriUtils;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/twitter")
public class Twitter {

    private static final String CLIENT_ID = "VdDxuSGwZEmYGJZI5nGMTQ971";
    private static final String CLIENT_SECRET = "O9XXPmRpVi7DymI3TT4Mvw1G50ggGTwxcDAVzQq4J7cPSZjVOU";

    private static final String INNER_ACCESS_TOKEN = "738007413341757440-2D7kjpnB9i6YJGbBDgc1r6HM2fq5r3D";
    private static final String INNER_ACCESS_TOKEN_SECRET = "Q1BoVTTiTtfRNc8iTGHXYzku7Qx6mAvpM0m2pXDFM6mBp";

    private static final String TWITTER_PREFIX = "api.twitter.com";

    private OAuthConsumer authConsumer;

    @Autowired
    private HttpClient httpClient;

    public Twitter() {
        setAuthConsumer(initAuthConsumer());
    }


    @RequestMapping(value = "/searchByName", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity searchByName(@RequestParam("name") String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, TWITTER_PREFIX, "/1.1/users/search.json", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        try {
            getAuthConsumer().sign(searchGet);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            e.printStackTrace();
        }
        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo",  method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public ResponseEntity getUserInfo(@RequestParam("id") String id, @RequestParam("nickname") String nickname)
            throws IOException, URISyntaxException {

        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("screen_name", nickname));
        nameValuePairs.add(new BasicNameValuePair("user_id", id));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, TWITTER_PREFIX, "/1.1/users/show.json", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        try {
            getAuthConsumer().sign(searchGet);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            e.printStackTrace();
        }
        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return ResponseEntity.ok(stringResponse);
    }

    private static OAuthConsumer initAuthConsumer() {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CLIENT_ID, CLIENT_SECRET);
        consumer.setTokenWithSecret(INNER_ACCESS_TOKEN, INNER_ACCESS_TOKEN_SECRET);
        return consumer;
    }

    public OAuthConsumer getAuthConsumer() {
        return authConsumer;
    }

    public void setAuthConsumer(OAuthConsumer oAuthConsumer) {
        this.authConsumer = oAuthConsumer;
    }

}
