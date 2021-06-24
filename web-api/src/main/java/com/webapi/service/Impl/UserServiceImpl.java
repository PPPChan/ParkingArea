package com.webapi.service.Impl;

import com.webapi.dataobject.UserInfo;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.UserInfoRepository;
import com.webapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/15 11:10
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository repository;

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserInfo findOne(String openid) {
        Optional<UserInfo> userInfoOptional= repository.findById(openid);
        if (!userInfoOptional.isPresent()){
            log.error("【查找用户】openid不存在，openid={}",openid);
            throw new ParkingareaException(ResultEnum.USER_NOT_EXiSTS);
        }
        return userInfoOptional.get();
    }

    @Override
    public UserInfo create(UserInfo userInfo) {
        if (repository.findById((userInfo.getOpenid())).isPresent()){
            log.error("【创建用户】openid已存在，openid={}",userInfo.getOpenid());
            throw new ParkingareaException(ResultEnum.USER_EXISTS);
        }
        return repository.save(userInfo);
    }



    @Override
    public UserInfo update(UserInfo userInfo) {
        UserInfo update = repository.getOne(userInfo.getOpenid());

        if(userInfo.getUserName()!=null){
            update.setUserName(userInfo.getUserName());
        }
        System.out.println(userInfo.getUserPhone());
        if(userInfo.getUserPhone()!=null){
            update.setUserPhone(userInfo.getUserPhone());
        }
        return repository.save(update);
    }


}
