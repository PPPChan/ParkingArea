package com.webapi.service;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Admin;
import com.webapi.dataobject.Employee;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 14:57
 * @Version 1.0
 */
public interface AdminService {
    //修改管理员信息
    Admin update(String newPassword);
    //管理员登录
    ResultVO login(Integer id, String password);
}
