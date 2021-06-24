package com.webapi.repository;

import com.webapi.dataobject.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/19 16:12
 * @Version 1.0
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking,Integer> {
    Parking findParkingByParkingName(String parkingName);
}
