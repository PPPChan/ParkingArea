package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Employee;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.EmployeeDTO;
import com.webapi.service.EmployeeService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 17:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/employee")
@Slf4j
@CrossOrigin

public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/create")
    //添加员工
    public ResultVO<String> create(@RequestParam("employeeName") String employeeName,
                                   @RequestParam("employeePassword") String employeePassword,
                                   @RequestParam("employeePhone") String employeePhone){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmployeePassword(employeePassword);
        employee.setEmployeePhone(employeePhone);
        Employee result = service.create(employee);
        return ResultVOUtil.success(result.getEmployeeId());
    }

    @PostMapping("/delete")
    //删除员工
    public ResultVO<String> delete(@RequestParam("employeeId") Integer employeeId){
        service.delete(employeeId);
        return ResultVOUtil.success("删除成功");
    }

    @GetMapping("/personal")
    //查看员工个人信息
    public ResultVO<EmployeeDTO> personal(@RequestParam("employeeId") Integer employeeId){
        Employee result  = service.findOne(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(result,employeeDTO);
        return ResultVOUtil.success(employeeDTO);
    }

    @PostMapping("/update")
    //更新员工信息
    public ResultVO<EmployeeDTO> update(@RequestParam(value = "employeeId") Integer employeeId,
                                        @RequestParam(required=false,value = "employeeName") String employeeName,
                                        @RequestParam(required=false,value = "employeePassword") String employeePassword,
                                        @RequestParam(required=false,value = "employeePhone") String employeePhone){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setEmployeePassword(employeePassword);
        employee.setEmployeePhone(employeePhone);
        Employee result = service.update(employee);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(result,employeeDTO);
        return ResultVOUtil.success(employeeDTO);
    }

    @GetMapping("/list")
    //查看员工列表
    public ResultVO<Employee> list(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest request = PageRequest.of(page,size);
        Page<Employee> employeePage = service.findAll(request);
        return ResultVOUtil.success(employeePage.getContent());
    }


    @PostMapping("/login")
    //登录
    public ResultVO<String> login(@RequestParam("employeeId") Integer employeeId,
                                  @RequestParam("password") String password){
        return service.login(employeeId,password);
    }
}
