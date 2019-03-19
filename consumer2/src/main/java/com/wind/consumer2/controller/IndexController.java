package com.wind.consumer2.controller;

import com.wind.consumer2.feign.IndexFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class IndexController {

    @Resource
    IndexFeign indexFeign;

    @GetMapping("/hi")
    public String index(String name) {

        //log.info("welcome to service: cloud-consumer2, {}", name);

        //return indexFeign.index("cloud-consumer2"); //gateway test more clearly

        return indexFeign.index(name);
    }

}
