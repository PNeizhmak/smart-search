package com.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author Pavel Neizhmak
 */
@EnableWebMvc
@Configuration
@PropertySources({
        @PropertySource("classpath:database.properties"),
        @PropertySource("classpath:color.properties"),
})
@ComponentScan(basePackages = "com")
public class SmartSearchConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Value("#{PropertySplitter.mapOfList('${color.definition}')}")
    private Map<String, List<String>> colorDefinition;

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

    @Bean
    public Map<String, List<String>> colorDefinition() {
        return getColorDefinition();
    }

    public Map<String, List<String>> getColorDefinition() {
        return colorDefinition;
    }
}
