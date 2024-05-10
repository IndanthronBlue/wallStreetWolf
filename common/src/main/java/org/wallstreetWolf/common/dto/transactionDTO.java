package org.wallstreetWolf.common.dto;

import lombok.Data;
import org.wallstreetWolf.common.pojo.*;

@Data
public class transactionDTO {
    private transaction transaction;
    private trader trader1;
    private trader trader2;
}
