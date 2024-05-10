package org.wallstreetWolf.traderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wallstreetWolf.common.response.GlobalResponse;
import org.wallstreetWolf.traderservice.service.feignOrderService;
import javax.annotation.Resource;

@RestController
@RequestMapping("/trader/order")
public class feignOrderController {
    @Resource
    private feignOrderService feignOrderService;

    @GetMapping("/hello")
    public String call(){
        return feignOrderService.call();
    }
}
