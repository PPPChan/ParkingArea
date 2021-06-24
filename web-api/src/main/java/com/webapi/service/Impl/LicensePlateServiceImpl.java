package com.webapi.service.Impl;

import com.webapi.dataobject.LicensePlate;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.LicensePlateRepository;
import com.webapi.service.LicensePlateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 15:32
 * @Version 1.0
 */
@Service
@Slf4j
public class LicensePlateServiceImpl implements LicensePlateService {
    @Autowired
    private LicensePlateRepository repository;

    @Override
    public String bind(LicensePlate licensePlate) {
        //车牌是否已被绑定
        if(repository.findById(licensePlate.getLicensePlateNumber()).isPresent()){
            log.error("【绑定车牌】该车牌号已被绑定，车牌号:{}",licensePlate.getLicensePlateNumber());
            throw new ParkingareaException(ResultEnum.LICENSEPLATE_NUMBER_EXISTS);
        }
        //绑定车牌是否以达上限5个
        int sum = repository.findByOpenid(licensePlate.getOpenid()).size();
        if(sum>=5){
            log.error("【绑定车牌】绑定车牌数已达上限,已绑车牌数：{}",sum);
            throw new ParkingareaException(ResultEnum.LICENSEPLATE_UPPER_LIMIT);
        }
        LicensePlate result = repository.save(licensePlate);
        return result.getLicensePlateNumber();
    }

    @Override
    public String unbind(LicensePlate licensePlate) {
        repository.delete(licensePlate);
        return licensePlate.getLicensePlateNumber();
    }

    @Override
    public List<LicensePlate> personal(String openid) {
        return repository.findByOpenid(openid);
    }
}
