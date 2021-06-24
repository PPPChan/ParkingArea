package com.webapi.service.Impl;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Admin;
import com.webapi.dataobject.Employee;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.AdminRepository;
import com.webapi.service.AdminService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 15:03
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository repository;


    @Override
    public Admin update(String newPassword){
        Admin update = repository.getOne(1);
        update.setPassword(newPassword);
        return repository.save(update);
    }

    @Override
    public ResultVO login(Integer id, String password) {
        Admin admin = repository.getOne(1);
        if(!admin.getPassword().equals(password)){
//            return ResultVOUtil.error(ResultEnum.EMPLOYEE_PASSWORD_ERROR);
            throw new ParkingareaException(ResultEnum.EMPLOYEE_PASSWORD_ERROR);
//            return ResultVOUtil.error();
        }
//        return "登录成功";
        return ResultVOUtil.success("登录成功");
    }
}
