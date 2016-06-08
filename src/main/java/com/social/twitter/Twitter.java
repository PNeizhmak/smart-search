package com.social.twitter;

import com.google.inject.Inject;
import com.model.ExtraParamsDto;
import com.model.IUserOperations;
import com.util.Constants;
import com.util.Utils;
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

import javax.ws.rs.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Pavel Neizhmak
 */
@Path("twitter")
public class Twitter implements IUserOperations {

    private static final String CLIENT_ID = "VdDxuSGwZEmYGJZI5nGMTQ971";
    private static final String CLIENT_SECRET = "O9XXPmRpVi7DymI3TT4Mvw1G50ggGTwxcDAVzQq4J7cPSZjVOU";

    private static final String INNER_ACCESS_TOKEN = "738007413341757440-2D7kjpnB9i6YJGbBDgc1r6HM2fq5r3D";
    private static final String INNER_ACCESS_TOKEN_SECRET = "Q1BoVTTiTtfRNc8iTGHXYzku7Qx6mAvpM0m2pXDFM6mBp";

    private static final String TWITTER_PREFIX = "api.twitter.com";

    private OAuthConsumer authConsumer;

    @Inject
    private HttpClient httpClient;

    public Twitter() {
        setAuthConsumer(initAuthConsumer());
    }


    @GET
    @Path("/searchByName/{name}")
    @Produces(APPLICATION_JSON)
    public String searchByName(@PathParam("name") final String name) throws IOException, URISyntaxException {
        HttpResponse response;

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("q", name));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, TWITTER_PREFIX, "/1.1/users/search.json", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        try {
            getAuthConsumer().sign(searchGet);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            e.printStackTrace();
        }
        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
    }

    @GET
    @Path("/getUserInfo/{id}")
    @Produces(APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") final String id, @MatrixParam("params") final List<String> jsonParamsMap)
            throws IOException, URISyntaxException {

        HttpResponse response;

        ExtraParamsDto paramsDto = Utils.parseExtraParams(jsonParamsMap.toString());
        final String nickname = paramsDto != null ? paramsDto.getNickname() : "";

        final List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("screen_name", nickname));
        nameValuePairs.add(new BasicNameValuePair("user_id", id));

        final URI uri = Utils.buildRequest(Constants.SCHEMA_HTTPS, TWITTER_PREFIX, "/1.1/users/show.json", nameValuePairs);
        HttpGet searchGet = new HttpGet(uri);

        try {
            getAuthConsumer().sign(searchGet);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            e.printStackTrace();
        }
        response = httpClient.execute(searchGet);

        final String stringResponse = EntityUtils.toString(response.getEntity());

        return Utils.buildResponse(stringResponse);
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
