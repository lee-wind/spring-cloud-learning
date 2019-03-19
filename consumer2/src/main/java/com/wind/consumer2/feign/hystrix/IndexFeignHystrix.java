package com.wind.consumer2.feign.hystrix;

import com.wind.consumer2.feign.IndexFeign;
import org.springframework.stereotype.Component;

@Component
public class IndexFeignHystrix implements IndexFeign {

    @Override
    public String index(String name) {
        return "provider server has error";
    }
}
