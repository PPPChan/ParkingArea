package com.webapi.repository;

import com.webapi.dataobject.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 陈俊鹏
 * @Date 2021/6/15 15:02
 * @Version 1.0
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

}
