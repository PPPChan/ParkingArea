package com.webapi.repository;

import com.webapi.dataobject.UserInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/14 15:43
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository repository;
    @Test
    void saveTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("张1三");
        userInfo.setOpenid("123skjdlk33das");
        userInfo.setUserPhone("13265555555");
        UserInfo result = repository.save(userInfo);
        Assert.assertNotNull(result);
    }
}