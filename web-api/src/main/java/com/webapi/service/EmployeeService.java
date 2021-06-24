package com.webapi.service;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 16:02
 * @Version 1.0
 */
public interface EmployeeService {
    //添加员工
    Employee create(Employee employee);
    //删除员工
    void delete(Integer employeeId);
    //查看员工个人信息
    Employee findOne(Integer employeeId);
    //查看员工列表
    Page<Employee> findAll(Pageable pageable);
    //修改员工信息
    Employee update(Employee employee);
    //员工登录
    ResultVO login(Integer employeeId, String password);
}
