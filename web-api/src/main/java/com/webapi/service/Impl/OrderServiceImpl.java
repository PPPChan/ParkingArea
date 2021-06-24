package com.webapi.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.webapi.dataobject.LicensePlate;
import com.webapi.dataobject.OrderMaster;
import com.webapi.dataobject.Parking;
import com.webapi.dto.OrderDataDTO;
import com.webapi.dto.OrderIdDTO;
import com.webapi.dto.OrderStatisticsDTO;
import com.webapi.enums.ResultEnum;
import com.webapi.exception.ParkingareaException;
import com.webapi.repository.LicensePlateRepository;
import com.webapi.repository.OrderMasterRepository;
import com.webapi.repository.ParkingRepository;
import com.webapi.service.OrderService;
import com.webapi.service.ParkingService;
import com.webapi.util.HttpRequest;
import com.webapi.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 15:29
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private LicensePlateRepository licensePlateRepository;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingRepository parkingRepository;


    @Override
    /**
     * 创建订单
     */

    public OrderIdDTO create(String licensePlateNumber, Integer parkingId) {
        //1.查询该车牌是否已绑定
        Optional<LicensePlate> optionalLicensePlate = licensePlateRepository.findById(licensePlateNumber);
        if (!optionalLicensePlate.isPresent()){
            //没绑定抛出异常
            log.error("【创建订单】该车牌号未绑定，车牌号：{}",licensePlateNumber);
            throw new ParkingareaException(ResultEnum.LICENSEPLATE_NUMBER_UNBIND);
        }
        //2.查询是否有空车位
        Parking parking = parkingService.findOne(parkingId);
        if(parking.getParkingAvailable()==0){
            log.error("【创建订单】该停车场无可用车位，parkingId:{}",parking.getParkingId());
            throw new ParkingareaException(ResultEnum.PARKING_NOT_AVAILABLE);
        }
        //3.获取openid
        LicensePlate licensePlate = optionalLicensePlate.get();
        String openid = licensePlate.getOpenid();
        //4.创建order对象并赋值
        OrderMaster orderMaster = new OrderMaster();
        //5.生成订单号
        String orderId = KeyUtil.genUniqueKey();
        orderMaster.setParkingName(parking.getParkingName());
        orderMaster.setUserOpenid(openid);
        orderMaster.setOrderId(orderId);
        orderMaster.setLicensePlateNumber(licensePlateNumber);
        orderMaster.setParkingId(parkingId);
        orderMaster.setHourPrice(parking.getHourPrice());
        //6.写入订单数据库
        OrderMaster result = orderMasterRepository.save(orderMaster);
        //7.加已用车位
        parkingService.increaseUsed(parkingId);
        OrderIdDTO orderIdDTO = new OrderIdDTO();
        orderIdDTO.setOrderId(result.getOrderId());
        return orderIdDTO;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findById(orderId);
        if (!optionalOrderMaster.isPresent()){
            log.error("【查询单个订单】orderId不存在，orderId：{}",orderId);
            throw new ParkingareaException(ResultEnum.ORDERID_NOT_EXISTS);
        }
        OrderMaster orderMaster = optionalOrderMaster.get();
        //更新预计金额
        //获取当前时间戳
        long currentTime = new Date().getTime();
        long dif = currentTime-orderMaster.getCreateTime().getTime();
        double hourDif = (double)dif/(double)3600000;
//        DecimalFormat hourPrice = new DecimalFormat("0.00");

//        BigDecimal d = new BigDecimal()
        BigDecimal hourPrice = orderMaster.getHourPrice().multiply(BigDecimal.valueOf(hourDif)).setScale(2,BigDecimal.ROUND_HALF_UP);

        orderMaster.setOrderAmount(hourPrice);
        //不存入数据库
//        orderMasterRepository.save(orderMaster);
        return orderMaster;
    }

    @Override
    public List<OrderMaster> personal(String openid) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findByUserOpenid(openid);

        return orderMasterList;
    }

    @Override
    public Page<OrderMaster> findAll(Pageable pageable) {
        return orderMasterRepository.findAll(pageable);
    }


    @Override
    public OrderMaster pay(String orderId) {
        OrderMaster orderMaster = findOne(orderId);
//        md5(payId+param+type+price+通讯密钥)
        String text = orderMaster.getOrderId()+1+orderMaster.getOrderAmount()+"aa15188ce0f1d97018524d9862ef2a46";
        String sign = DigestUtils.md5DigestAsHex(text.getBytes());
        String params = "payId="+orderMaster.getOrderId()+"&type=1"+"&price="+orderMaster.getOrderAmount()+"&sign="+sign;
        String res = HttpRequest.sendGet("http://localhost/createOrder",params);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if(jsonObject.getInteger("code")!=1){
            throw new ParkingareaException(jsonObject.getInteger("code"), jsonObject.getString("msg"));
        }
        return orderMaster;
    }


    @Override
    public OrderMaster finish(String orderId,BigDecimal bigDecimal){
        Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findById(orderId);
        if (!optionalOrderMaster.isPresent()){
            log.error("【查询单个订单】orderId不存在，orderId：{}",orderId);
            throw new ParkingareaException(ResultEnum.ORDERID_NOT_EXISTS);
        }
        OrderMaster orderMaster = optionalOrderMaster.get();
        orderMaster.setOrderAmount(bigDecimal);
        orderMaster.setPayStatus(1);

        parkingService.decreaseUsed(orderMaster.getParkingId());

        return orderMasterRepository.save(orderMaster);
    }

    @Override
    public List<OrderStatisticsDTO> statistics(){
        long now = System.currentTimeMillis() / 1000l;
        long daysecond = 60 * 60 * 24;
        long daytime = now - (now + 8 * 3600) % daysecond;
        List<Parking> parkings =  parkingRepository.findAll();

        List<OrderStatisticsDTO> orderStatisticsDTOS = new ArrayList<>();

        for(Parking parking:parkings){
            OrderStatisticsDTO orderStatisticsDTO = new OrderStatisticsDTO();
            orderStatisticsDTO.setParkingName(parking.getParkingName());
            System.out.println(parking.getParkingId());

            List<Integer> count = new ArrayList<>();
            List<BigDecimal> amount = new ArrayList<>();


            //近七天
            for (int day=6;day>=0;day--){
                //今天0点
                Date start = new Date((daytime-(day*daysecond))*1000);
                //今天2359
                Date end = new Date((daytime-((day-1)*daysecond))*1000);
                OrderDataDTO orderDataDTO = orderMasterRepository.findGroupByParkingId(start,end,parking.getParkingId());
                if(orderDataDTO==null){
                    count.add(0);
                    amount.add(new BigDecimal(0));
                }else{
                    count.add(orderDataDTO.getOrderCount());
                    amount.add(orderDataDTO.getOrderAmount());
                }
//                System.out.println("start:"+start+"end:"+end);
            }
            orderStatisticsDTO.setOrderCount(count);
            orderStatisticsDTO.setAmountSum(amount);
            orderStatisticsDTOS.add(orderStatisticsDTO);
        }

        return orderStatisticsDTOS;

    }
}
