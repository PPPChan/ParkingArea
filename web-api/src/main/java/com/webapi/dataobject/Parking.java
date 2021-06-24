package com.webapi.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webapi.util.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/13 15:32
 * @Version 1.0
 */
@Data
@Entity
@DynamicUpdate
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //停车场编号
    private Integer parkingId;
    //停车场名称
    private String parkingName;
    //停车场地址
    private String parkingAddress;
    //价格
    private BigDecimal hourPrice;
    //停车位总数
    private Integer parkingTotal;
    //已停车位
    private Integer parkingUsed = 0;
    //可用车位
    private Integer parkingAvailable;
    //停车场状态
    private Integer parkingStatus = 1;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //修改时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
