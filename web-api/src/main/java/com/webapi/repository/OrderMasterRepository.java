package com.webapi.repository;

import com.webapi.dataobject.OrderMaster;
import com.webapi.dto.OrderDataDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 15:03
 * @Version 1.0
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
//    long now = System.currentTimeMillis() / 1000l;
//    long daysecond = 60 * 60 * 24;
//    long daytime = now - (now + 8 * 3600) % daysecond;
    @Query("select o from OrderMaster o where o.userOpenid = ?1 order by o.createTime DESC ")
    List<OrderMaster> findByUserOpenid(String userOpenid);
//    @Query("select o.parkingName as parkingName ,count(o) as orderCount ,sum(o.orderAmount) as orderAmount from OrderMaster o \n" +
//            "where o.createTime between ?1 and ?2 \n" +
//            "group by o.parkingId")
//    List<OrderDataDTO> findGroupByParkingId(Date startTime, Date endTime);
//    DATE_FORMAT(FROM_UNIXTIME(add_date),’%Y-%m-%d’) as day
    @Query("select o.parkingName as parkingName ,count(o) as orderCount ,sum(o.orderAmount) as orderAmount from OrderMaster o \n" +
            "where o.createTime >= ?1 and o.createTime <= ?2 and o.parkingId = ?3\n" +
            "group by o.parkingId")
    OrderDataDTO findGroupByParkingId(Date start, Date end,Integer parkingId);
}
