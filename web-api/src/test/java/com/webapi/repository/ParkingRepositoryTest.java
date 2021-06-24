package com.webapi.repository;

import com.webapi.dataobject.Parking;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 17:10
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ParkingRepositoryTest {
    @Autowired
    private ParkingRepository repository;
    @Test
    void findParkingByParkingName() {
        String parkingName = "炫炫炫1停车场";
        Parking result = repository.findParkingByParkingName(parkingName);
        System.out.println(result);
    }
}