package org.wallstreetWolf.orderservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallstreetWolf.common.pojo.order;

import java.util.Collections;
import java.util.List;

@Service
public class orderExecuteService {
    @Autowired
    orderService orderService;
    @Autowired
    marketBuyService marketBuyService;
    @Autowired
    marketSellService marketSellService;
    @Autowired
    transactionService transactionService;

    public order createLimitOrder(order order){
        orderService.save(order);
        ExecuteLimit(order);
        if(order.getOrderType()==0){
            marketBuyService.addOrderToBuyMarket(order);
        } else if (order.getOrderType()==1) {
            marketSellService.addOrderToSellMarket(order);
        }
        return order;
    }

    public void ExecuteLimit(order order){
        if(order.getOrderType()==0){
            ExecuteLimitBuy(order);
        }else if(order.getOrderType()==1){
            ExecuteLimitSell(order);
        }
    }

    public void ExecuteLimitBuy(order order){
        QueryWrapper<order> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",order.getProductId())
                .eq("order_type",1)
                .eq("broker_id",order.getBrokerId())
                .eq("order_algorithm",1)
                .gt("remain_quantity",0)
                .le("price",order.getPrice());

        List<order> orderSells = orderService.list(wrapper);
        if(orderSells.isEmpty()){
            return;
        }
        Collections.sort(orderSells);
        int remaining = order.getQuantity();
        for(int i=0; i<orderSells.size() && remaining>0 ; i++){
            order sellOrder = orderSells.get(i);
            int delete_vol = 0;
            if(sellOrder.getRemainQuantity()>=remaining){
                delete_vol = remaining;
                sellOrder.setRemainQuantity(sellOrder.getRemainQuantity()-remaining);
                remaining = 0;
            }else{
                delete_vol = sellOrder.getRemainQuantity();
                remaining -= sellOrder.getRemainQuantity();
                sellOrder.setRemainQuantity(0);
            }
            orderService.updateById(sellOrder);
            marketSellService.deleteFromSellMarket(sellOrder.getProductId(), sellOrder.getPrice(), delete_vol);
            transactionService.addTransaction(sellOrder,order,delete_vol);
        }
        order.setRemainQuantity(remaining);
        orderService.updateById(order);
    }

    public void ExecuteLimitSell(order order){
        QueryWrapper<order> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",order.getProductId())
                .eq("order_type",0)
                .eq("broker_id",order.getBrokerId())
                .eq("order_algorithm",1)
                .gt("remain_quantity",0)
                .ge("price",order.getPrice());

        List<order> orderBuys = orderService.list(wrapper);
        if(orderBuys.isEmpty()){
            return;
        }
        Collections.sort(orderBuys);
        int remaining = order.getQuantity();
        for(int i=0; i<orderBuys.size() && remaining>0 ; i++){
            order buyOrder = orderBuys.get(i);
            int delete_vol = 0;
            if(buyOrder.getRemainQuantity()>=remaining){
                delete_vol = remaining;
                buyOrder.setRemainQuantity(buyOrder.getRemainQuantity()-remaining);
                remaining = 0;
            }else{
                delete_vol = buyOrder.getRemainQuantity();
                remaining -= buyOrder.getRemainQuantity();
                buyOrder.setRemainQuantity(0);
            }
            orderService.updateById(buyOrder);
            marketBuyService.deleteFromBuyMarket(buyOrder.getProductId(), buyOrder.getPrice(), delete_vol);
            transactionService.addTransaction(order,buyOrder,delete_vol);
        }
        order.setRemainQuantity(remaining);
        orderService.updateById(order);
    }
}
