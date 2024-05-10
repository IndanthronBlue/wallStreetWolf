package org.wallstreetWolf.orderservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wallstreetWolf.common.pojo.marketBuy;
import org.wallstreetWolf.common.pojo.order;

public interface marketBuyService extends IService<marketBuy> {
    void addOrderToBuyMarket(order order);
    void deleteFromBuyMarket(int product_id, Long price, int delete_vol);
}
