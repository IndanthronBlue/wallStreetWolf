package org.wallstreetWolf.orderservice.service.serviceImple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wallstreetWolf.common.mapper.marketSellMapper;
import org.wallstreetWolf.common.pojo.marketSell;
import org.wallstreetWolf.common.pojo.order;
import org.wallstreetWolf.orderservice.service.marketSellService;

@Service
public class markerSellServiceImpl extends ServiceImpl<marketSellMapper, marketSell> implements marketSellService {

    @Autowired
    marketSellMapper marketSellMapper;

    @Override
    public void addOrderToSellMarket(order order) {
        if(order.getRemainQuantity()==0){
            return;
        }
        marketSell marketSell = new marketSell();
        marketSell.setProductId(order.getProductId());
        marketSell.setPrice(order.getPrice());
        marketSell.setSellVol(order.getRemainQuantity());

        QueryWrapper<marketSell> wrapper = new QueryWrapper<>();
        wrapper.select("sell_vol")
                .eq("product_id",order.getProductId())
                .eq("price",order.getPrice());
        marketSell mb = marketSellMapper.selectOne(wrapper);
        if(mb!=null){
            marketSell.setSellVol(order.getRemainQuantity() + mb.getSellVol());
            marketSellMapper.delete(wrapper);
        }
        marketSellMapper.insert(marketSell);
    }

    @Override
    public void deleteFromSellMarket(int product_id, Long price, int delete_vol) {
        QueryWrapper<marketSell> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",product_id)
                .eq("price",price);
        marketSell mb = marketSellMapper.selectOne(wrapper);

        marketSell marketSell = new marketSell();
        marketSell.setProductId(product_id);
        marketSell.setPrice(price);

        if(mb!=null){
            int remain_vol = mb.getSellVol() - delete_vol;
            marketSell.setSellVol(remain_vol);
            marketSellMapper.delete(wrapper);
            if(remain_vol!=0){
                marketSellMapper.insert(marketSell);
            }
        }
    }
}
