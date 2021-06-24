package com.webapi.service;

import com.webapi.dataobject.LicensePlate;

import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 15:30
 * @Version 1.0
 */
public interface LicensePlateService {
    //绑定车牌
    String bind(LicensePlate licensePlate);
    //解除绑定
    String unbind(LicensePlate licensePlate);
    //查看个人车牌
    List<LicensePlate> personal(String openid);
}
