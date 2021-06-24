package com.webapi.controller;

import com.webapi.VO.ResultVO;
import com.webapi.dataobject.OrderMaster;
import com.webapi.dataobject.UserInfo;
import com.webapi.dto.OrderIdDTO;
import com.webapi.service.OrderService;
import com.webapi.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/20 23:21
 * @Version 1.0
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/order")
public class OrderMasterController {
    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public ResultVO<OrderIdDTO> create(@RequestParam("licensePlateNumber") String licensePlateNumber,
                                       @RequestParam("parkingId") Integer parkingId){
        OrderIdDTO orderIdDTO = service.create(licensePlateNumber,parkingId);
        return ResultVOUtil.success(orderIdDTO);
    }

    @GetMapping("/detail")
    public ResultVO<OrderMaster> detail(@RequestParam("orderId") String orderId){
        return ResultVOUtil.success(service.findOne(orderId));
    }

    @GetMapping("/personal")
    public ResultVO<List<OrderMaster>> personal(@RequestParam("openid") String openid){
        return ResultVOUtil.success(service.personal(openid));
    }

    @GetMapping("/list")
    public ResultVO<Page<OrderMaster>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "size",defaultValue = "2") Integer size){
        PageRequest request = PageRequest.of(page-1,size, Sort.Direction.DESC,"createTime");
        Page<OrderMaster> orderMasterPage = service.findAll(request);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> attr = new HashMap<>();
        System.out.println("totalPage"+orderMasterPage.getTotalPages());
        attr.put("totalPage",orderMasterPage.getTotalPages());
        attr.put("currentPage",page);
        map.put("data",orderMasterPage.getContent());
        map.put("attr",attr);
        return ResultVOUtil.success(map);
    }

    //取消该接口
    @PostMapping("/pay")
    public ResultVO<OrderMaster> pay(@RequestParam(value = "orderId") String orderId){
        return ResultVOUtil.success(service.pay(orderId));
    }

    //支付完成调用
    @PostMapping("/finish")
    public ResultVO<OrderMaster> finish(@RequestParam(value = "orderId") String orderId,
                                   @RequestParam(value = "amount") BigDecimal amount){
        return ResultVOUtil.success(service.finish(orderId,amount));
    }

    //统计数据
    @GetMapping("/statistics")
    public ResultVO<OrderMaster> finish(){
        return ResultVOUtil.success(service.statistics());
    }

}
