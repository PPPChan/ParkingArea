package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.UserInfoDTO;
import com.webapi.service.UserService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/17 12:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/userinfo")
@CrossOrigin
@Slf4j
public class UserInfoController {
    @Autowired
    private UserService service;
    @GetMapping("/list")
    //用户列表
    public ResultVO<UserInfo> list(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest request = PageRequest.of(page,size);
        Page<UserInfo> userInfoPagePage = service.findAll(request);
        return ResultVOUtil.success(userInfoPagePage.getContent());
    }

    @PostMapping("/create")
    //创建用户
    public ResultVO<String> create(@RequestParam("openid") String openid,
                         @RequestParam("userName") String userName,
                         @RequestParam("phone") String phone){
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        userInfo.setUserName(userName);
        userInfo.setUserPhone(phone);
        UserInfo result = service.create(userInfo);
        return ResultVOUtil.success(result.getOpenid());
    }

    @GetMapping("/personal")
    //查看个人信息
    public ResultVO<UserInfoDTO> personal(@RequestParam("openid") String openid){

        UserInfoDTO userInfoDTO = service.findOne(openid);
        return ResultVOUtil.success(userInfoDTO);
    }

    @PostMapping("/update")
    //更新用户信息
    public ResultVO<UserInfoDTO> update(@RequestParam("openid") String openid,
                                        @RequestParam(required=false,value = "userName") String userName,
                                        @RequestParam(required=false,value = "phone") String phone){
        UserInfo userIno = new UserInfo();
        userIno.setOpenid(openid);
        userIno.setUserName(userName);
        userIno.setUserPhone(phone);
        UserInfo result = service.update(userIno);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(result,userInfoDTO);
        return ResultVOUtil.success(userInfoDTO);
    }


    @PostMapping("/openMonthlyCard")
    //开通月卡
    public ResultVO<Integer> openMonthlyCard(@RequestParam("openid") String openid){
        Integer res = service.openMonthlyCard(openid);
        return ResultVOUtil.success(res);
    }

    @GetMapping("/getExpire")
    //获取到期时间
    public ResultVO<String> getExpire(@RequestParam("openid") String openid){
        return ResultVOUtil.success(service.getExpire(openid));
    }

}
