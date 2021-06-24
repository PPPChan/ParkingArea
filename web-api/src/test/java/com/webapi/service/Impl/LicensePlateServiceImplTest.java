package com.webapi.service.Impl;

import com.webapi.dataobject.LicensePlate;
import com.webapi.service.LicensePlateService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 16:07
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LicensePlateServiceImplTest {
    @Autowired
    private LicensePlateService service;
    @Test
    void bind() {
        LicensePlate licensePlate =  new LicensePlate();
        licensePlate.setLicensePlateNumber("京A12345");
        licensePlate.setOpenid("123456");
        String number = service.bind(licensePlate);
        Assert.assertEquals(number,"京A12345");
    }

    @Test
    void unbind() {
    }

    @Test
    void personal() {
    }
}