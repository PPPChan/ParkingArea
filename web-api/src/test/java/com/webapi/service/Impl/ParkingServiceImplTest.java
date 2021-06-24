package com.webapi.service.Impl;

import com.webapi.dataobject.Parking;
import com.webapi.dto.ParkingIdDTO;
import com.webapi.service.ParkingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 17:57
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ParkingServiceImplTest {
    @Autowired
    private ParkingService service;
    @Test
    void create() {
        String name = "海利停车场";
        Integer total = 90;
        String address = "海利花园";
        BigDecimal hourPrice = new BigDecimal(11);
        ParkingIdDTO result = service.create(name,address,total,hourPrice);
        Assert.assertNotNull(result);
    }

    @Test
    void findOne() {
        Parking parking = service.findOne(1);
        System.out.println(parking.getParkingName());
        Assert.assertNotNull(parking);
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,5);
        Page<Parking> parkings = service.findAll(request);
        Assert.assertNotEquals(0,parkings.getTotalElements());
    }

    @Test
    void update() {
        Parking result = service.update(1,"汕尾停车场","广东省汕尾市汕尾大道",43,new BigDecimal(13));
        Assert.assertEquals(result.getParkingName(),"汕尾停车场");
    }

    @Test
    void close() {
        Parking parking = service.close(2);
    }

    @Test
    void increase() {
        Parking parking = service.findOne(1);
        Integer start = parking.getParkingUsed();
        service.increaseUsed(1);
        Parking parking2 = service.findOne(1);
        Integer end = parking2.getParkingUsed();
        Assert.assertNotEquals(start,end);
    }

    @Test
    void decrease() {
        Parking parking = service.findOne(1);
        Integer start = parking.getParkingUsed();
        service.decreaseUsed(1);
        Parking parking2 = service.findOne(1);
        Integer end = parking2.getParkingUsed();
        Assert.assertNotEquals(start,end);
    }
}