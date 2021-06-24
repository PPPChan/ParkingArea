package com.webapi.service.Impl;

import com.webapi.dataobject.UserInfo;
import com.webapi.service.UserService;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/15 11:18
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService service;

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,6);
        Page<UserInfo> userInfoPage = service.findAll(request);
        System.out.println(userInfoPage);
        System.out.println(userInfoPage.getTotalElements());
        Assert.assertNotEquals(0,userInfoPage.getTotalElements());
    }

    @Test
    void findOne() {
        UserInfo userInfo = service.findOne("123skjdlk33das");
        System.out.println(userInfo.getOpenid());
        System.out.println(userInfo.getUserName());
        System.out.println(userInfo.getUserPhone());
        Assert.assertEquals("123skjdlk33das",userInfo.getOpenid());
    }

    @Test
    void create() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("李四");
        userInfo.setOpenid("321312sdadads");
        userInfo.setUserPhone("13222222222");
        UserInfo result = service.create(userInfo);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid("123456");
        userInfo.setUserName("theshy");
        UserInfo update = service.update(userInfo);
        Assert.assertEquals("theshy",service.findOne("123456").getUserName());
    }
}