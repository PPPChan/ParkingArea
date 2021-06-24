package com.webapi.service.Impl;

import com.webapi.dataobject.OrderMaster;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.OrderDataDTO;
import com.webapi.dto.OrderIdDTO;
import com.webapi.dto.OrderStatisticsDTO;
import com.webapi.service.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/20 19:49
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService service;

    @Test
    void create() {
        String licenseNumber = "京A12345";
        Integer parkingId = 3;
        OrderIdDTO orderIdDTO = service.create(licenseNumber,parkingId);
        System.out.println(orderIdDTO.getOrderId());
    }

    @Test
    void findOne() {
        String orderId = "1618922092011635989";
        OrderMaster orderMaster = service.findOne(orderId);
        System.out.println(orderMaster.getOrderAmount());
        Assert.assertEquals(orderId,orderMaster.getOrderId());
    }

    @Test
    void personal() {
        String openid = "123456";
        PageRequest request = PageRequest.of(0,6);
        List<OrderMaster> orderMasterList = service.personal(openid);
//        Assert.assertNotEquals(0,orderMasterList.getTotalElements());
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,6);
        Page<OrderMaster> orderMasters = service.findAll(request);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }

    @Test
    void pay(){
        String orderId = "1618924705935125513";
        OrderMaster orderMaster = service.pay(orderId);


    }

    @Test
    void statistics(){
//        long now = System.currentTimeMillis() / 1000l;
        List<OrderStatisticsDTO> orderDataDTOS =  service.statistics();
        System.out.println(orderDataDTOS);
        System.out.println(orderDataDTOS.size());
//        for (OrderDataDTO orderDataDTO:orderDataDTOS){
//            System.out.println(orderDataDTO.getOrderAmount());
//            System.out.println(orderDataDTO.getOrderCount());
//            System.out.println(orderDataDTO.getParkingName());
//        }
    }
}