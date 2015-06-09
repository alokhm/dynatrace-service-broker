package org.cloudfoundary.covisint.servicebroker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Class: Application.java
 * Description: Main application entry point and configuration
 *
 *@version 1.0, 2015-06-01
 *@author Lingeshm
 *
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class Application {

    /**
     * Start method
     *
     * @param args command line argument
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    /**
     * 
     * @return bean of FilterRegistrationBean class
     */
    @Bean
    FilterRegistrationBean brokerApiVersionFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new BrokerApiVersionFilter());
        bean.addUrlPatterns("/v2/*");

        return bean;
    }
    /**
     * 
     * @return object of ObjectMapper class
     */
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}