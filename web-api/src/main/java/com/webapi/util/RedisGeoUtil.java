package com.webapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @Author 陈俊鹏
 * @Date 2021/8/6 17:21
 * @Version 1.0
 */
@Component
public class RedisGeoUtil {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    /**
     * @Title: geoAdd
     * @Description: TODO(添加geo)
     * @param key key
     * @param point 经纬度
     * @param member 成员
     * @return Long 返回影响的行
     */
    public Long geoAdd(String key, Point point, String member) {
        return redisTemplate.boundGeoOps(key).add(point,member);
//        return stringRedisTemplate.opsForGeo().geoAdd(key, point, member);
    }

    /**
     * @Title: geoRemove
     * @Description: TODO(删除成员)
     * @param key
     * @param members 成员
     * @return Long 返回影响的行
     */
    public Long geoRemove(String key, String... members) {
        return redisTemplate.boundGeoOps(key).remove(members);
    }

    /**
     * @Title: geoPos
     * @Description: TODO(查询地址的经纬度)
     * @param key key
     * @param members 成员
     * @return List<Point>
     */
    public List<Point> geoPos(String key, String... members) {
        return redisTemplate.boundGeoOps(key).position(members);
    }

    /**
     * @Title: geoHash
     * @Description: TODO(查询位置的geohash)
     * @param key
     * @param members
     * @return List<String>
     */
    public List<String> geoHash(String key, String... members) {
        return redisTemplate.boundGeoOps(key).hash(members);
    }

    /**
     * @Title: geoDist
     * @Description: TODO(查询2位置距离)
     * @param key key
     * @param member1 成员1
     * @param member2 成员2
     * @param metric 单位
     * @return Double 距离
     */
    public Double geoDist(String key, String member1, String member2, Metric metric) {
        return redisTemplate.boundGeoOps(key).distance(member1,member2,metric).getValue();
    }



}