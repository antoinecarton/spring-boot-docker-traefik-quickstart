package com.example.service;

import com.example.configuration.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloServiceImpl implements HelloService {

    private final HelloProperties helloProperties;

    @Autowired
    public HelloServiceImpl(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @Override
    public String hello(String name) {
        if (StringUtils.hasText(name)) {
            return String.format("Hello %s from %s", name, helloProperties.getFrom());
        }
        return "Hello John Doe from " + helloProperties.getFrom();
    }
}
