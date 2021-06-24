package com.webapi.dto;

import lombok.Data;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 17:57
 * @Version 1.0
 */
@Data
public class EmployeeDTO {
    private Integer employeeId;
    //员工姓名
    private String employeeName;
    //员工电话
    private String employeePhone;
}
