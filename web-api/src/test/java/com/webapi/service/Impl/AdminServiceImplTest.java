package com.webapi.service.Impl;

import com.webapi.dataobject.Admin;
import com.webapi.service.AdminService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 15:13
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService service;



    @Test
    void update() {
        Admin admin = service.update("123456");
        Assert.assertEquals(admin.getPassword(),"123456");
    }

    @Test
    void login() {
        System.out.println(service.login(4,"2"));


    }
}