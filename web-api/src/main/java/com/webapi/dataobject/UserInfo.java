package com.webapi.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webapi.util.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/13 15:32
 * @Version 1.0
 */

@Entity
@Data
@DynamicUpdate
public class UserInfo {
    @Id
    //用户openid
    private String openid;
    //用户名字
    private String userName;
    //用户电话
    private String userPhone;
    //用户类型 默认为0 0为普通用户 1为月卡用户
    private Integer userType = 0;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
