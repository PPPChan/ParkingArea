package com.webapi.repository;

import com.webapi.dataobject.OrderMaster;
import com.webapi.dto.OrderDataDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 17:56
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    @Test
    void findGroupByParkingId() {
        long now = System.currentTimeMillis() / 1000l;
        long daysecond = 60 * 60 * 24;
        long daytime = now - (now + 8 * 3600) % daysecond;
        //今天0点
        Date start = new Date((daytime-(0*daysecond))*1000);
        //今天2359
        Date end = new Date((daytime-((0-1)*daysecond))*1000);

        OrderDataDTO orderDataDTOS =  repository.findGroupByParkingId(start,end,1);

        if (orderDataDTOS==null){
            System.out.println("null");
        }
//        System.out.println(orderDataDTOS.getOrderCount());

        ////        new Date(1618838000),new Date(1618948000)
//
//        System.out.println(orderDataDTOS.size());
//        for (OrderDataDTO o: orderDataDTOS){
//            System.out.println(o.getOrderCount());
//            System.out.println(o.getParkingName());
//            System.out.println(o.getOrderAmount());
//        }

    }

    @Test
    void findByOpenid(){
        System.out.println(repository.findByUserOpenid("123123"));
    }
}