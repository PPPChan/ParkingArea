package com.webapi.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 12:06
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体内容
    private T data;
}
