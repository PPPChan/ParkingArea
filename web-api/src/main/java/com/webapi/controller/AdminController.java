package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Admin;
import com.webapi.dataobject.Employee;
import com.webapi.dto.EmployeeDTO;
import com.webapi.service.AdminService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 22:09
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin
public class AdminController {
    @Autowired
    public AdminService adminService;


    @PostMapping("/update")
    //修改密码
    public ResultVO<Admin> update(@RequestParam(value = "password") String password){
        Admin admin =  adminService.update(password);
        return ResultVOUtil.success(admin);
    }


    @PostMapping("/login")
    //登录
    public ResultVO<String> login(@RequestParam("adminId") Integer adminId,
                                  @RequestParam("password") String password){
        return adminService.login(adminId,password);
    }
}
