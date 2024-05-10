package org.wallstreetWolf.orderservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wallstreetWolf.common.pojo.marketSell;
import org.wallstreetWolf.common.pojo.order;

public interface marketSellService extends IService<marketSell>{
    void addOrderToSellMarket(order order);
    void deleteFromSellMarket(int product_id, Long price, int delete_vol);
}
