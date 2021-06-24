package com.webapi.enums;

import lombok.Getter;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 10:40
 * @Version 1.0
 */

@Getter
public enum ResultEnum {
    USER_EXISTS(10,"用户openid已存在"),
    USER_NOT_EXiSTS(11,"openid不存在"),
    EMPLOYEE_NOT_EXISTS(12,"employeeId不存在"),
    LICENSEPLATE_NUMBER_EXISTS(13,"车牌号已被绑定"),
    LICENSEPLATE_UPPER_LIMIT(14,"已绑车牌已达上限"),
    LICENSEPLATE_NUMBER_UNBIND(15,"当前车牌号未绑定"),
    PARKING_NAME_EXISTS(16,"停车场名字已存在"),
    PARKING_ID_NOT_EXISTS(17,"parkingId不存在"),
    PARKING_HAS_FULL(18,"已用车位已满"),
    PARKING_IS_EMPTY(19,"已用车位为0"),
    PARKING_TOTAL_ERROR(20,"停车位总数不能低于已停车位"),
    PARKING_NOT_AVAILABLE(21,"该停车场无可用车位"),
    ORDERID_NOT_EXISTS(22,"orderId不存在"),
    EMPLOYEE_PASSWORD_ERROR(23,"员工密码错误");

//    PARAM_ERROR(1,"参数不正确"),
//    PRODUCT_NOT_EXISTS(10,"商品不存在"),
//    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
//    ORDER_NOT_EXIST(12,"订单不存在"),
//    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
//    ORDER_STATUS_ERROR(14,"订单状态不正确"),
//    ORDER_UPDATE_FAIL(15,"订单更新失败"),
//    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
//    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
//    CART_EMPTY(18,"购物车不能为空"),
//    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
//    WX_MP_ERROR(20,"微信公众账号方面错误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
