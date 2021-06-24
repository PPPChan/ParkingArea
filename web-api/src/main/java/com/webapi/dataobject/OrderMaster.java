package com.webapi.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webapi.util.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/13 15:32
 * @Version 1.0
 */
@Data
@DynamicUpdate
@Entity
public class OrderMaster {
    @Id
    //订单号
    private String orderId;
    //停车场编号
    private Integer parkingId;
    //停车场名字
    private String parkingName;
    //每小时价格
    private BigDecimal hourPrice;
    //用户openid
    private String userOpenid;
    //车牌号
    private String licensePlateNumber;
    //订单金额
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
