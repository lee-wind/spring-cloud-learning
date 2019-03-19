package com.wind.consumer2.feign;

import com.wind.consumer2.feign.hystrix.IndexFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-provider", fallback = IndexFeignHystrix.class)
public interface IndexFeign {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String index(@RequestParam("name") String name);
}
