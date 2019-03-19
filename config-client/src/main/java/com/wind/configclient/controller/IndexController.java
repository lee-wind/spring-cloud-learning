package com.wind.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${foo}")
    String foo;

    @RequestMapping("/foo")
    public String index(){

        return foo;
    }
}
