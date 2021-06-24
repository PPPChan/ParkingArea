package com.webapi.handler;

import com.webapi.VO.ResultVO;
import com.webapi.exception.ParkingareaException;
import com.webapi.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 陈俊鹏
 * @Date 2021/5/5 23:21
 * @Version 1.0
 */
@ControllerAdvice
public class ParkingareaExceptionHandler {
    @ExceptionHandler(value = ParkingareaException.class)
    @ResponseBody
    public ResultVO handlerParkingException(ParkingareaException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
