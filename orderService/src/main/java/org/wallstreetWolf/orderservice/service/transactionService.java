package org.wallstreetWolf.orderservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.common.pojo.transaction;

public interface transactionService extends IService<transaction>{
    void addTransaction(order order1, order order2, int quantity);
}
