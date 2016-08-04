package com.social.forsquare;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.core.Response;

import com.util.Constants;
import com.util.UriUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Neizhmak
 */
@RestController
@RequestMapping("/rest/forsquare")
public class Forsquare {

    private static final String CLIENT_ID = "NX5BPZ41CRUBI1YLSTH5KQABRSYTOKLNDLZKHNEDIVXMWCOD";
    private static final String CLIENT_SECRET = "IOKHL1YTP5UFQWG5NW4XZBH0L4E2HQ5YPQERPSDHTQ445Y1F";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String RESPONSE_TYPE = "code";
    private static final String REDIRECT_URI = "http://localhost:8081/";
    private static final String FS_PREFIX = "api.foursquare.com/v2";

    private static final String TEMP_ACCESS_TOKEN = "GZG2R0LGWSV0VY4IUCYOFVFZDYL5FTADKHQ52P55O5ZZAGKF";

    @Autowired
    private HttpClient httpClient;

    @RequestMapping(value = "/searchByName/{name}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public Response searchByName(@PathVariable("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("oauth_token", TEMP_ACCESS_TOKEN));
        nameValuePairs.add(new BasicNameValuePair("v", "20131016"));
        nameValuePairs.add(new BasicNameValuePair("name", name));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, FS_PREFIX, "/users/search", nameValuePairs);
        HttpPost searchPost = new HttpPost(uri);

        response = httpClient.execute(searchPost);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET, produces = Constants.APP_JSON_UTF_8)
    public Response getUserInfo(@PathVariable("id") final String id, @MatrixVariable(value = "params", required = false) final List<String> jsonParamsMap)
        throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("oauth_token", TEMP_ACCESS_TOKEN));
        nameValuePairs.add(new BasicNameValuePair("v", "20131016 "));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, FS_PREFIX, "/users/" + id, nameValuePairs);
        HttpGet getUserInfo = new HttpGet(uri);
        response = httpClient.execute(getUserInfo);

        String stringResponse = EntityUtils.toString(response.getEntity());

        return UriUtils.buildResponse(stringResponse);
    }

    private String getAccessToken() throws URISyntaxException, IOException {
        HttpResponse response;

        //1st request format
        /**
         * https://foursquare.com/oauth2/authenticate
           ?client_id=YOUR_CLIENT_ID
            &response_type=code
            &redirect_uri=YOUR_REGISTERED_REDIRECT_URI
         */

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("client_id", CLIENT_ID));
        nameValuePairs.add(new BasicNameValuePair("response_type", RESPONSE_TYPE));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));

        final URI uri = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, "foursquare.com", "/oauth2/authenticate", nameValuePairs);

        HttpGet searchGet = new HttpGet(uri);

        response = new DefaultHttpClient().execute(searchGet);

        //response:
        //http://localhost:8081/?code=GNXIO02JW1R2CZVMD1RZHXFHXCHTYPGA5532CVRMON0NIRS0#_=_


        //2nd request format
        /**
         * https://foursquare.com/oauth2/access_token
           ?client_id=YOUR_CLIENT_ID
           &client_secret=YOUR_CLIENT_SECRET
           &grant_type=authorization_code
           &redirect_uri=YOUR_REGISTERED_REDIRECT_URI
           &code=CODE
         */

        nameValuePairs.clear();
        nameValuePairs.add(new BasicNameValuePair("client_id", CLIENT_ID));
        nameValuePairs.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        nameValuePairs.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));
        nameValuePairs.add(new BasicNameValuePair("code", "P20CSX3SCECN4053ZEWPGJE5GGHDDZS4LEUHE1L5KIFCTVKF"));

        final URI uri1 = UriUtils.buildRequest(Constants.SCHEMA_HTTPS, "foursquare.com", "/oauth2/access_token", nameValuePairs);

        HttpGet searchGet1 = new HttpGet(uri1);

        response = new DefaultHttpClient().execute(searchGet1);

        //response format is:
        //{"access_token":"GZG2R0LGWSV0VY4IUCYOFVFZDYL5FTADKHQ52P55O5ZZAGKF"}

        return response.toString();
    }
}
