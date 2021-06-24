package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.LicensePlate;
import com.webapi.service.LicensePlateService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 16:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/licenseplate")
@CrossOrigin
@Slf4j
public class LicensePlateController {
    @Autowired
    private LicensePlateService service;
    @PostMapping("/bind")
    public ResultVO<String> bind(@RequestParam("openid") String openid,
                                 @RequestParam("licensePlateNumber") String licensePlateNumber) {
        LicensePlate licensePlate = new LicensePlate();
        licensePlate.setLicensePlateNumber(licensePlateNumber);
        licensePlate.setOpenid(openid);
        String result = service.bind(licensePlate);
        return  ResultVOUtil.success(result);
    }
    @PostMapping("/unbind")
    public ResultVO<String> unbind(@RequestParam("openid") String openid,
                                   @RequestParam("licensePlateNumber") String licensePlateNumber){
        LicensePlate licensePlate = new LicensePlate();
        licensePlate.setLicensePlateNumber(licensePlateNumber);
        licensePlate.setOpenid(openid);
        String result = service.unbind(licensePlate);
        return  ResultVOUtil.success(result);
    }
    @GetMapping("/personal")
    public ResultVO<List<LicensePlate>> personal(@RequestParam("openid") String openid){
        List<LicensePlate> result =  service.personal(openid);
        return ResultVOUtil.success(result);
    }
}
