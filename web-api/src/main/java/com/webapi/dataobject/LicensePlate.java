package com.webapi.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/13 15:31
 * @Version 1.0
 */
@Data
@Entity
public class LicensePlate {

    @Id
    //车牌号
    private String licensePlateNumber;
    //用户openid
    private String openid;
}
