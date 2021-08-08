package com.webapi.service.Impl;

import cn.hutool.core.date.DateUnit;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.UserInfoDTO;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.UserInfoRepository;
import com.webapi.service.UserService;
import com.webapi.util.RedisUtil;
import javafx.util.converter.DateStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserInfoDTO findOne(String openid) {
        Optional<UserInfo> userInfoOptional= repository.findById(openid);
        if (!userInfoOptional.isPresent()){
            log.error("【查找用户】openid不存在，openid={}",openid);
            throw new ParkingareaException(ResultEnum.USER_NOT_EXiSTS);
        }

        UserInfo userInfo = userInfoOptional.get();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo,userInfoDTO);
        if (redisUtil.hasKey("userType."+openid)){
            userInfoDTO.setUserType(1);
        }


        return userInfoDTO;
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


    //开通月卡
    @Override
    public Integer openMonthlyCard(String openid) {
        //key的格式为：userType.openid
        //判断用户是否为月卡用户
        if (redisUtil.hasKey("userType."+openid)){
            //是，增加一个月的过期时间（提前续费）
            long timeout = redisUtil.getExpire("userType."+openid);
            redisUtil.set("userType."+openid,"1",timeout+2626560);
        }else{
            //不是，设置新的过期时间
            redisUtil.set("userType."+openid,"1",2626560 );
        }
        log.info( "userType."+openid+":"+String.valueOf(redisUtil.getExpire("userType."+openid)));
        return 1;
    }

    @Override
    public String getExpire(String openid) {
        //判断用户是否为月卡用户
        if (!redisUtil.hasKey("userType."+openid)){

            //不是，抛异常
            log.error("【查询月卡过期时间】该用户不是月卡用户，openid={}",openid);
            throw new ParkingareaException(ResultEnum.USER_NOT_OPENCARD);

        }

        //是，返回过期时间
        long timeout = redisUtil.getExpire("userType."+openid);
        int day = (int) (timeout/86400);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,day);


        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(calendar.getTime());

        return str;

    }
}
