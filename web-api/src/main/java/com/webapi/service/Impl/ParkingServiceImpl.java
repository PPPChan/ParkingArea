package com.webapi.service.Impl;

import com.webapi.dataobject.Parking;
import com.webapi.dto.ParkingDistanceDTO;
import com.webapi.dto.ParkingIdDTO;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.ParkingRepository;
import com.webapi.service.ParkingService;
import com.webapi.util.RedisGeoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 17:06
 * @Version 1.0
 */
@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private ParkingRepository repository;
    @Autowired
    private RedisGeoUtil redisGeoUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    private String key = "parking";


    @Override
    public ParkingIdDTO create(String parkingName,String parkingAddress, Integer parkingTotal,BigDecimal hourPrice,double longitude,double latitude) {
        if(repository.findParkingByParkingName(parkingName) != null){
            log.error("【添加停车场】停车场名字已存在，停车场名字：{}",parkingName);
            throw new ParkingareaException(ResultEnum.PARKING_NAME_EXISTS);
        }
        Parking parking = new Parking();
        parking.setParkingName(parkingName);
        parking.setParkingTotal(parkingTotal);
        parking.setParkingAvailable(parkingTotal);
        parking.setParkingAddress(parkingAddress);
        parking.setHourPrice(hourPrice);
        Parking result =repository.save(parking);

        //地理位置存入redis
        Point point = new Point(longitude,latitude);
        redisGeoUtil.geoAdd(key,point,String.valueOf(result.getParkingId()));

        ParkingIdDTO parkingIdDTO = new ParkingIdDTO();
        parkingIdDTO.setParkingId(result.getParkingId());
        return parkingIdDTO;
    }

    @Override
    public Parking findOne(Integer parkingId) {
        Optional<Parking> optionalParking = repository.findById(parkingId);
        if (!optionalParking.isPresent()){
            log.error("【查询停车场】该parkingId不存在，parkingId：{}",parkingId);
            throw new ParkingareaException(ResultEnum.PARKING_ID_NOT_EXISTS);
        }
        return optionalParking.get();
    }

    @Override
    public Page<Parking> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Parking update(Integer parkingId, String parkingName, String parkingAddress, Integer parkingTotal, BigDecimal hourPrice) {
        Parking parking = findOne(parkingId);
        if(parkingTotal<parking.getParkingUsed()){
            log.error("【更新停车场信息】停车位总数不可再减少");
            throw new ParkingareaException(ResultEnum.PARKING_TOTAL_ERROR);
        }
        //设置修改后的可用车位数
        parking.setParkingAvailable(parkingTotal- parking.getParkingUsed());
        parking.setParkingAddress(parkingAddress);
        parking.setParkingName(parkingName);
        parking.setParkingTotal(parkingTotal);
        parking.setHourPrice(hourPrice);



        return repository.save(parking);
    }

    @Override
    public String updateGeo(String parkingId,double longitude,double latitude){
        //地理位置存入redis
        Point point = new Point(longitude,latitude);
        redisGeoUtil.geoAdd(key,point,parkingId);
        return "更新地理位置成功！";
    }

    @Override
    public Parking close(Integer parkingId) {
        Parking parking = findOne(parkingId);
        parking.setParkingStatus(0);
        return repository.save(parking);
    }

    @Override
    public Parking open(Integer parkingId) {
        Parking parking = findOne(parkingId);
        parking.setParkingStatus(1);
        return repository.save(parking);
    }

    @Override
    public Parking increaseUsed(Integer parkingId) {
        Parking parking = findOne(parkingId);
        Integer used = parking.getParkingUsed();

        if(used>= parking.getParkingTotal()){
            log.error("【增加已用车位】已用车位已满");
            throw new ParkingareaException(ResultEnum.PARKING_HAS_FULL);
        }
        //加已用车位
        parking.setParkingUsed(used+1);
        //减空车位
        Integer available = parking.getParkingAvailable();
        parking.setParkingAvailable(available-1);
        return repository.save(parking);
    }

    @Override
    public Parking decreaseUsed(Integer parkingId) {
        Parking parking = findOne(parkingId);
        Integer used = parking.getParkingUsed();
        if(used<= 0){
            log.error("【减少已用车位】已用车位为0，不可减少");
            throw new ParkingareaException(ResultEnum.PARKING_IS_EMPTY);
        }
        //减已用车位
        parking.setParkingUsed(used-1);
        //加空车位
        Integer available = parking.getParkingAvailable();
        parking.setParkingAvailable(available+1);
        return repository.save(parking);
    }

    @Override
    public List<ParkingDistanceDTO> radius(double longitude, double latitude){
        List<ParkingDistanceDTO> parkingDistanceList = new ArrayList<>();
        //longitude,latitude
        Circle circle = new Circle(longitude,latitude, 500000);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().sortAscending().limit(6);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo()
                .radius(key,circle,args);
//        System.out.println(results);

        for (GeoResult<RedisGeoCommands.GeoLocation<String>> r: results){
//            System.out.println("name:"+r.getContent().getName()+",distance:"+r.getDistance().in(Metrics.KILOMETERS));
            Parking parking = findOne(Integer.valueOf(r.getContent().getName()));
            ParkingDistanceDTO parkingDistance = new ParkingDistanceDTO();

            BeanUtils.copyProperties(parking,parkingDistance);
            parkingDistance.setDistance(BigDecimal.valueOf(r.getDistance().in(Metrics.KILOMETERS).getValue()).setScale(2,BigDecimal.ROUND_UP));
            parkingDistanceList.add(parkingDistance);

        }
        return parkingDistanceList;
    }
}
