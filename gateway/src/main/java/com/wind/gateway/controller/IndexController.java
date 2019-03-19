package com.wind.gateway.controller;

import com.wind.gateway.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @PostMapping("/login")
    //@GetMapping("/login")
    public String login(String userId){

        if(userId.equals("21")){
            Map<String, Object> map = new HashMap<>();
            map.put("sub", userId);
            map.put("created", new Date());

            return JwtUtil.create(map);
        }

        return null;
    }
}
