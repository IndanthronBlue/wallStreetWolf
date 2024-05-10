package org.wallstreetWolf.common.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("market_sell")
public class marketSell {
    private int productId;
    private Integer levelBuy;
    private Integer buyVol;
    private Long price;
    private Integer sellVol;
    private Integer levelSell;
}
