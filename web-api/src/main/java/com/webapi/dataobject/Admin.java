package com.webapi.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 14:59
 * @Version 1.0
 */

@Data
@Entity
@DynamicUpdate
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //管理员id
    private Integer id;
    //管理员密码
    private String password;
}
