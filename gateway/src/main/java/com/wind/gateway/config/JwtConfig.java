package com.wind.gateway.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
@ConfigurationProperties()
public class JwtConfig {

    private String secret;
    private long intervalMillis;

    @PostConstruct
    public void init(){

        log.info("secret: {}", secret);
        log.info("intervalMillis: {}", intervalMillis);
    }
}
