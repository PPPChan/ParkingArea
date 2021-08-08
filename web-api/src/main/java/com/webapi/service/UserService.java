package com.webapi.service;

import com.webapi.dataobject.UserInfo;
import com.webapi.dto.UserInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/14 16:58
 * @Version 1.0
 */
public interface UserService {
    //查询用户列表
    Page<UserInfo> findAll (Pageable pageable);
    //查询用户个人信息
    UserInfoDTO findOne(String openid);
    //创建用户
    UserInfo create(UserInfo userInfo);
    //更新用户
    UserInfo update(UserInfo userInfo);

    //开通月卡
    Integer openMonthlyCard(String openid);

    //查看月卡过期时间
    String getExpire(String openid);

}
