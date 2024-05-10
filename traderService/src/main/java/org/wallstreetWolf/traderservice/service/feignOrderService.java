package org.wallstreetWolf.traderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "orderService")
public interface feignOrderService {
    @GetMapping("/order/hello")
    String call();
}
