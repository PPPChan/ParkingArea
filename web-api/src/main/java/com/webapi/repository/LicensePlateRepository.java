package com.webapi.repository;

import com.webapi.dataobject.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/18 15:28
 * @Version 1.0
 */
@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate,String> {
    List<LicensePlate> findByOpenid(String orderId);
}
