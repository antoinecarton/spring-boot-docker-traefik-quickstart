package com.example.configuration;

import com.example.service.HelloService;
import com.example.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.Constants.BeanNames.EXAMPLE_SERVICE;

@Configuration
@EnableConfigurationProperties({HelloProperties.class})
public class HelloConfiguration {

    @Bean(name = EXAMPLE_SERVICE, autowire = Autowire.BY_NAME)
    public HelloService exampleService() {
        return new HelloServiceImpl();
    }
}
