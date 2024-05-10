package org.wallstreetWolf.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class product {
    @TableId(type = IdType.AUTO)
    private int productId;
    private String productName;
}
