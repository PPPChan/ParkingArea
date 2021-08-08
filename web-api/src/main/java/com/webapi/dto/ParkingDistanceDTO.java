package com.webapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author 陈俊鹏
 * @Date 2021/8/6 23:55
 * @Version 1.0
 */
@Data
public class ParkingDistanceDTO {
    //name
    private String parkingName;
    //address
    private String parkingAddress;
    //distance
    private BigDecimal distance = new BigDecimal(0.00);
    //可用车位
    private Integer parkingAvailable;

}
