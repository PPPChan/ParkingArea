package com.webapi.service;

import com.webapi.dataobject.Parking;
import com.webapi.dto.ParkingDistanceDTO;
import com.webapi.dto.ParkingIdDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 16:42
 * @Version 1.0
 */
public interface ParkingService {
    //新建停车场
    ParkingIdDTO create(String parkingName, String parkingAddress, Integer parkingTotal, BigDecimal hourPrice,double longitude,double latitude);
    //查询单个停车场
    Parking findOne(Integer parkingId);
    //查询所有停车场
    Page<Parking> findAll(Pageable pageable);
    //更新停车场信息
    Parking update(Integer parkingId,String parkingName,String parkingAddress,Integer parkingTotal,BigDecimal hourPrice);
    //更新停车场地理位置
    String updateGeo(String parkingId,double longitude,double latitude);
    //关闭停车场
    Parking close(Integer parkingId);
    //开启停车场
    Parking open(Integer parkingId);
    //增加已用车位
    Parking increaseUsed(Integer parkingId);
    //减少已用车位
    Parking decreaseUsed(Integer ParkingId);

    //查找附近的停车场
    List<ParkingDistanceDTO> radius(double longitude, double latitude);
}
