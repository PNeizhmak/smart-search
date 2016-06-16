package com.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.social.facebook.Facebook;
import com.social.github.Github;
import com.social.google.GooglePlus;
import com.social.instagram.Instagram;
import com.social.twitter.Twitter;
import com.social.vk.Vk;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class Config extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(Vk.class);
                bind(Instagram.class);
                bind(Facebook.class);
                bind(Github.class);
                bind(Twitter.class);
                bind(GooglePlus.class);
                serve("*").with(GuiceContainer.class);
            }

            @Provides
            public HttpClient buildHttpClient() {
                return HttpClients.createDefault();
            }
        });
    }
}
