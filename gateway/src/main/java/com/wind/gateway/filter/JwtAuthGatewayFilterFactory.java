package com.wind.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wind.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthGatewayFilterFactory extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {

            String jwt = exchange.getRequest().getHeaders()
                    .getFirst("Authorization");

            try{
                Claims claims = JwtUtil.getClaims(jwt);

                log.info("claims: {}", String.valueOf(claims));
            } catch (ExpiredJwtException eje){
                return authFail(exchange, "expiration");
            } catch (Exception e){
                return authFail(exchange, "jwt error");
            }

            return  chain.filter(exchange);
        };
    }

    private Mono<Void> authFail(ServerWebExchange exchange, String message) {

        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        Map<String, String> result = new HashMap<>();
        result.put("status", "110");
        result.put("message", message);

        DataBuffer dataBuffer = null;
        try {
            dataBuffer = response.bufferFactory()
                    .wrap(new ObjectMapper().writeValueAsString(result).getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response.writeWith(Mono.just(dataBuffer));
    }
}
