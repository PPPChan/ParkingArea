package com.webapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webapi.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/8/6 13:36
 * @Version 1.0
 */
@Data
public class OrderMasterDTO {
    //订单号
    private String orderId;
    //停车场名字
    private String parkingName;
    //每小时价格
    private BigDecimal hourPrice;
    //车牌号
    private String licensePlateNumber;
    //订单原价
    private BigDecimal cost = new BigDecimal(0);
    //订单折扣金额
    private BigDecimal discount = new BigDecimal(0);
    //订单预计金额
    private BigDecimal orderAmount = new BigDecimal(0);
    //订单支付状态
    private Integer payStatus = 0;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //结束时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;


}
