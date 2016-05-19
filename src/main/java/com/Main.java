package com;

import com.social.instagram.Instagram;
import com.social.vk.Vk;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
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
            final HttpClient httpClient = HttpClients.createDefault();
            vk.searchUsersByName(httpClient);
            vk.getPersonalInfoById(httpClient);

            instagram.getPersonalInfoById(httpClient);
            instagram.searchUsersByName(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
