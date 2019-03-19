package com.wind.provider1.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Provider1IndexController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "indexError")
    public String index(String name){

        log.info(name);
        return "Hello " + name + ", I am " + applicationName + ": " + port;
    }

    public String indexError(String name){

        return "provider1 server has error";
    }
}
