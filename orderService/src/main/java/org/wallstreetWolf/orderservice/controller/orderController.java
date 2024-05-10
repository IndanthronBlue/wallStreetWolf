package org.wallstreetWolf.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.common.response.GlobalResponse;
import org.wallstreetWolf.orderservice.service.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class orderController{
    @Autowired
    orderExecuteService orderExecuteService;
    @Autowired
    orderService orderService;

    @PostMapping("/addLimitOrder")
    public order createLimitOrder(@RequestBody order order){
        return orderExecuteService.createLimitOrder(order);
    }

    @GetMapping("/list")
    public List<order> listOrder(){
        return orderService.list();
    }

    @GetMapping("/getById")
    public order getById(@RequestParam("id") Long id){
        return orderService.getById(id);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(){
        return "hello, this is order service from " + port;
    }
}
