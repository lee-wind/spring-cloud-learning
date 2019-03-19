package com.wind.consumer1.feign.hystrix;

import com.wind.consumer1.feign.IndexFeign;
import org.springframework.stereotype.Component;

@Component
public class IndexFeignHystrix implements IndexFeign {

    @Override
    public String index(String name) {
        return "provider server has error";
    }
}
