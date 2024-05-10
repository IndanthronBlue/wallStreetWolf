package org.wallstreetWolf.orderservice.service.serviceImple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallstreetWolf.common.mapper.marketBuyMapper;
import org.wallstreetWolf.common.pojo.marketBuy;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.orderservice.service.marketBuyService;

@Service
public class marketBuyServiceImple extends ServiceImpl<marketBuyMapper, marketBuy> implements marketBuyService {
    @Autowired
    marketBuyMapper marketBuyMapper;

    @Override
    public void addOrderToBuyMarket(order order) {
        if(order.getRemainQuantity()==0){
            return;
        }
        marketBuy marketBuy = new marketBuy();
        marketBuy.setProductId(order.getProductId());
        marketBuy.setPrice(order.getPrice());
        marketBuy.setBuyVol(order.getRemainQuantity());

        QueryWrapper<marketBuy> wrapper = new QueryWrapper<>();
        wrapper.select("buy_vol")
                .eq("product_id",order.getProductId())
                .eq("price",order.getPrice());
        marketBuy mb = marketBuyMapper.selectOne(wrapper);
        if(mb!=null){
            marketBuy.setBuyVol(order.getRemainQuantity() + mb.getBuyVol());
            marketBuyMapper.delete(wrapper);
        }
        marketBuyMapper.insert(marketBuy);
    }

    @Override
    public void deleteFromBuyMarket(int product_id, Long price, int delete_vol) {
        QueryWrapper<marketBuy> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",product_id)
                .eq("price",price);
        marketBuy mb = marketBuyMapper.selectOne(wrapper);

        marketBuy marketBuy = new marketBuy();
        marketBuy.setProductId(product_id);
        marketBuy.setPrice(price);

        if(mb!=null){
            int remain_vol = mb.getBuyVol() - delete_vol;
            marketBuy.setBuyVol(remain_vol);
            marketBuyMapper.delete(wrapper);
            if(remain_vol!=0){
                marketBuyMapper.insert(marketBuy);
            }
        }
    }
}
