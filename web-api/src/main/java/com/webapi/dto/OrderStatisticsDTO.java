package com.webapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 21:03
 * @Version 1.0
 */
@Data
public class OrderStatisticsDTO {
    private String parkingName;

    private List<Integer> orderCount;

    private List<BigDecimal> amountSum;
}
