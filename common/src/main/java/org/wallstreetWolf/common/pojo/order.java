package org.wallstreetWolf.common.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;


@Data
@TableName("`order`")
public class order implements Comparable<order> {

    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;

    private int orderType;

    private int productId;

    private String brokerId;

    private int quantity;

    private Long price;

    private int remainQuantity;

    private String traderId;

    private Integer orderAlgorithm;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Override
    public int compareTo(order o) {
        return (int) (this.price - o.price);
    }
}