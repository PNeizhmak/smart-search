package com;

import com.facebook.Facebook;
import com.social.instagram.Instagram;
import com.social.vk.Vk;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author Pavel Neizhmak
 */
public class Main {

    public static void main(String[] args) {
        try {
            Instagram instagram = new Instagram();
            Vk vk = new Vk();
            Facebook facebook = new Facebook();

            final HttpClient httpClient = HttpClients.createDefault();

            System.out.println("\ncalling " + Vk.class);
            vk.searchUsersByName(httpClient);
            vk.getPersonalInfoById(httpClient);

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
