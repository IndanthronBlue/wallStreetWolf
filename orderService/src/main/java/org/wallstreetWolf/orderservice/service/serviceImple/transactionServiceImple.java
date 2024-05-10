package org.wallstreetWolf.orderservice.service.serviceImple;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallstreetWolf.common.mapper.transactionMapper;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.common.pojo.transaction;
import org.wallstreetWolf.orderservice.service.transactionService;

@Service
public class transactionServiceImple extends ServiceImpl<transactionMapper, transaction> implements transactionService {
    @Autowired
    transactionMapper transactionMapper;

    @Override
    public void addTransaction(order order1, order order2, int quantity) {
        transaction transaction = new transaction();
        transaction.setBrokerId(order1.getBrokerId());
        transaction.setProductId(order1.getProductId());
        transaction.setPrice(order1.getPrice());
        transaction.setQuantity(quantity);
        transaction.setTrader1Id(order1.getTraderId());
        transaction.setTrader1Side(order1.getOrderType());
        transaction.setTrader2Id(order2.getTraderId());
        transaction.setTrader2Side(order2.getOrderType());
        transaction.setOrder1Id(order1.getOrderId());
        transaction.setOrder2Id(order2.getOrderId());

        transactionMapper.insert(transaction);
    }
}
