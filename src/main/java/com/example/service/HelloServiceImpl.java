package com.example.service;

import com.example.configuration.HelloProperties;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class HelloServiceImpl implements HelloService {

    @Resource
    private HelloProperties helloProperties;

    @Override
    public String hello(String name) {
        if (StringUtils.hasText(name)) {
            return String.format("Hello %s from %s", name, helloProperties.getFrom());
        }
        return "Hello John Doe from " + helloProperties.getFrom();
    }
}
