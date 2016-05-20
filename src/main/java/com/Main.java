package com;

import java.io.IOException;

import com.social.facebook.Facebook;
import com.social.instagram.Instagram;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Pavel Neizhmak
 */
public class Main {

    public static void main(String[] args) {
        try {
            Instagram instagram = new Instagram();
            Facebook facebook = new Facebook();

            final HttpClient httpClient = HttpClients.createDefault();

            System.out.println("\ncalling " + Instagram.class);
            instagram.getPersonalInfoById(httpClient);
            instagram.searchUsersByName(httpClient);

            System.out.println("\ncalling " + Facebook.class);
            facebook.searchUsersByName(httpClient);
            facebook.getPersonalInfoById(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
