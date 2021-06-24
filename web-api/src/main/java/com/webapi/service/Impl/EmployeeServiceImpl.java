package com.webapi.service.Impl;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Employee;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.EmployeeRepository;
import com.webapi.service.EmployeeService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 16:16
 * @Version 1.0
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }


    @Override
    public void delete(Integer employeeId){
        repository.deleteById(employeeId);
    }
    @Override
    public Employee findOne(Integer employeeId) {
        Optional<Employee> employeeOptional= repository.findById(employeeId);
        if (!employeeOptional.isPresent()){
            log.error("【查找员工】employeeId不存在，openid={}",employeeId);
            throw new ParkingareaException(ResultEnum.EMPLOYEE_NOT_EXISTS);
        }
        return employeeOptional.get();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Employee update(Employee employee) {
        Employee update = repository.getOne(employee.getEmployeeId());

        if(employee.getEmployeeName()!=null){
            update.setEmployeeName(employee.getEmployeeName());
        }
        if(employee.getEmployeePassword()!=null){
            update.setEmployeePassword(employee.getEmployeePassword());
        }
        if(employee.getEmployeePhone()!=null){
            update.setEmployeePhone(employee.getEmployeePhone());
        }

        return repository.save(update);
    }

    @Override
    public ResultVO login(Integer employeeId, String password) {
        Employee employee = findOne(employeeId);
        if(!employee.getEmployeePassword().equals(password)){
//            return ResultVOUtil.error(ResultEnum.EMPLOYEE_PASSWORD_ERROR);
            throw new ParkingareaException(ResultEnum.EMPLOYEE_PASSWORD_ERROR);
//            return ResultVOUtil.error();
        }
//        return "登录成功";
        return ResultVOUtil.success("登录成功");
    }


}
