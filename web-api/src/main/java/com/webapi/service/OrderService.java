package com.webapi.service;

import com.webapi.dataobject.OrderMaster;
import com.webapi.dto.OrderDataDTO;
import com.webapi.dto.OrderIdDTO;
import com.webapi.dto.OrderStatisticsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 15:06
 * @Version 1.0
 */
public interface OrderService {
    //创建订单
    OrderIdDTO create(String licensePlateNumber,Integer parkingId);
    //查看订单详情
    OrderMaster findOne(String orderId);
    //查看个人订单列表
    List<OrderMaster> personal(String openid);
    //查询所有订单列表
    Page<OrderMaster> findAll(Pageable pageable);
    //支付订单
    OrderMaster pay(String orderId);
    //补单
    OrderMaster finish(String orderId, BigDecimal bigDecimal);

    //订单统计（数据可视化）
    List<OrderStatisticsDTO> statistics();

}
