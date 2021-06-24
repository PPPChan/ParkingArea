package com.webapi.repository;

import com.webapi.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/4/14 9:31
 * @Version 1.0
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

}
