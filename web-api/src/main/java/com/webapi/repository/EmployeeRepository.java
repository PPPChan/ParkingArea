package com.webapi.repository;

import com.webapi.dataobject.Employee;
import com.webapi.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 17:00
 * @Version 1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
