package com.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Pavel Neizhmak
 */
@EnableWebMvc
@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan(basePackages = "com")
public class SmartSearchConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DriverManagerDataSource getDataSource() {

        final String dbUrl = env.getRequiredProperty("database.url");
        final String dbUser = env.getRequiredProperty("database.user");
        final String dbPass = env.getRequiredProperty("database.password");
        final String dbDriver = env.getRequiredProperty("database.driver");

        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPass);
        dataSource.setDriverClassName(dbDriver);

        return dataSource;
    }

    @Bean
    public HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }
}
