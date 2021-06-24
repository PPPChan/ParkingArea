package com.webapi.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webapi.util.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/13 15:31
 * @Version 1.0
 */
@Data
@Entity
@DynamicUpdate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    //员工密码
    private String employeePassword;
    //员工姓名
    private String employeeName;
    //员工电话
    private String employeePhone;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //修改时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
