package com.webapi.service.Impl;

import com.webapi.dataobject.Employee;
import com.webapi.dataobject.UserInfo;
import com.webapi.service.EmployeeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 17:04
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService service;
    @Test
    void create() {
        Employee employee = new Employee();
        employee.setEmployeeName("员工乙");
        employee.setEmployeePassword("213211");
        Employee result = service.create(employee);
        Assert.assertNotNull(result);
    }

    @Test
    void findOne() {
        Integer id = 2;
        Employee result = service.findOne(id);
        Assert.assertEquals(result.getEmployeeId(),id);
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,6);
        Page<Employee> employeePage = service.findAll(request);
        System.out.println(employeePage);
        System.out.println(employeePage.getTotalElements());
        Assert.assertNotEquals(0,employeePage.getTotalElements());
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeePassword("123123");
        Employee result = service.update(employee);
        Assert.assertEquals("123123",result.getEmployeePassword());
    }

    @Test
    void login() {
        System.out.println(service.login(4,"12345646789"));

    }
}