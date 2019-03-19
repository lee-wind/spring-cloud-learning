package com.wind.provider4.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Provider4IndexController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "indexError")
    public String index(String name){

        return "Hello " + name + ", I am " + applicationName + ": " + port;
    }

    public String indexError(String name){

        return "provider4 server has error";
    }
}
