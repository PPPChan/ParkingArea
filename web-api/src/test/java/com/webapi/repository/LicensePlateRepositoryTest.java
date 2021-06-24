package com.webapi.repository;

import com.webapi.dataobject.LicensePlate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 15:45
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LicensePlateRepositoryTest {
    @Autowired
    private LicensePlateRepository repository;
    @Test
    void findByOpenid() {
        List<LicensePlate> l = repository.findByOpenid("11111111");
        System.out.println(l);
    }
}