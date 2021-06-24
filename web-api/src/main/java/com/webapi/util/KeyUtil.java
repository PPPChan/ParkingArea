package com.webapi.util;

import java.util.Random;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 16:03
 * @Version 1.0
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    //synchronized多线程关键字
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
