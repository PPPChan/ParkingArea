package com.webapi.util;

import com.webapi.VO.ResultVO;
import com.webapi.enums.ResultEnum;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 12:38
 * @Version 1.0
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultVO success(){
        return success(null);
    }


    public static ResultVO error(Integer code,String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("失败");
        return resultVO;
    }
}
