package com.webapi.exception;

import com.webapi.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 10:39
 * @Version 1.0
 */
@Getter
public class ParkingareaException extends RuntimeException{
    private Integer code;

    public ParkingareaException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ParkingareaException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
