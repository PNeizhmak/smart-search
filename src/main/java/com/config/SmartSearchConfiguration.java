package com.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Pavel Neizhmak
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com")
public class SmartSearchConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/user_db", "root", "root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }
}
