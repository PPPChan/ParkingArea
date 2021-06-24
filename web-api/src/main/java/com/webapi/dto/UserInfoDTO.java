package com.webapi.dto;

import lombok.Data;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 14:43
 * @Version 1.0
 */
@Data
public class UserInfoDTO {
    //用户openid
    private String openid;
    //用户名字
    private String userName;
    //用户电话
    private String phone;
    //用户类型 默认为0 0为普通用户 1为月卡用户
    private Integer userType;
}
