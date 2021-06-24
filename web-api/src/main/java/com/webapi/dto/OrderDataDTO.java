package com.webapi.dto;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.util.List;


/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 16:27
 * @Version 1.0
 */


public interface OrderDataDTO {

    Integer getOrderCount();

    String getParkingName();

    BigDecimal getOrderAmount();

}
