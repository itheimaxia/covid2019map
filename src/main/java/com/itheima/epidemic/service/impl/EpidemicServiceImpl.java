package com.itheima.epidemic.service.impl;

import com.itheima.epidemic.common.Constant;
import com.itheima.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取所有疫情数据
     * @return 所有疫情数据
     */
    @Override
    public String findAll() {
        String totalInfoJson = redisTemplate.opsForValue().get(Constant.REDIS_KEY_EPIDEMIC_JSON);
        return totalInfoJson;
    }

    /**
     * 根据省份名字,获取对应省份的疫情信息
     * @param provinceShortName 省份名称(简写)
     * @return 该省份疫情数据
     */
    @Override
    public String findProvinceByShortName(String provinceShortName) {
        String proInfoJson = (String)redisTemplate.opsForHash().
                get(Constant.REDIS_KEY_EPIDEMIC_PROVINCE,provinceShortName);
        return proInfoJson;
    }
}