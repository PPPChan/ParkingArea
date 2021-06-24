package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.Parking;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.ParkingIdDTO;
import com.webapi.service.ParkingService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/20 15:50
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/parking")
@Slf4j
public class ParkingController {
    @Autowired
    private ParkingService service;


    @PostMapping("/create")
    public ResultVO<ParkingIdDTO> create(@RequestParam("parkingName") String parkingName,
                                         @RequestParam("parkingAddress") String parkingAddress,
                                         @RequestParam("parkingTotal") Integer parkingTotal,
                                         @RequestParam("hourPrice") BigDecimal hourPrice){
        ParkingIdDTO parkingIdDTO = service.create(parkingName,parkingAddress, parkingTotal,hourPrice);
        return ResultVOUtil.success(parkingIdDTO);
    }

    @PostMapping("/update")
    public ResultVO<Parking> update(@RequestParam("parkingId") Integer parkingId,
                                    @RequestParam("parkingName") String parkingName,
                                    @RequestParam("parkingAddress") String parkingAddress,
                                    @RequestParam("parkingTotal") Integer parkingTotal,
                                    @RequestParam("hourPrice") BigDecimal hourPrice){
        Parking parking = service.update(parkingId,parkingName,parkingAddress,parkingTotal,hourPrice);
        return ResultVOUtil.success(parking);
    }

    @PostMapping("/close")
    public ResultVO<Parking> close(@RequestParam("parkingId") Integer parkingId){
        Parking parking = service.close(parkingId);
        return ResultVOUtil.success(parking);
    }

    @PostMapping("/open")
    public ResultVO<Parking> open(@RequestParam("parkingId") Integer parkingId){
        Parking parking = service.open(parkingId);
        return ResultVOUtil.success(parking);
    }

    @PostMapping("/increaseUsed")
    public ResultVO<Parking> increaseUsed(@RequestParam("parkingId") Integer parkingId){
        Parking parking = service.increaseUsed(parkingId);
        return ResultVOUtil.success(parking);
    }

    @PostMapping("/decreaseUsed")
    public ResultVO<Parking> decrease(@RequestParam("parkingId") Integer parkingId){
        Parking parking = service.decreaseUsed(parkingId);
        return ResultVOUtil.success(parking);
    }

    @GetMapping("/detail")
    public ResultVO<Parking> findOne(@RequestParam("parkingId") Integer parkingId) {
        Parking parking = service.findOne(parkingId);
        return ResultVOUtil.success(parking);
    }

    @GetMapping("/list")
    public ResultVO<Parking> list(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                  @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest request = PageRequest.of(page,size);
        Page<Parking> parkingPage = service.findAll(request);
        return ResultVOUtil.success(parkingPage.getContent());
    }
}
