package com.itheima.epidemic.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.epidemic.common.Constant;
import com.itheima.epidemic.pojo.DataInfo;
import com.itheima.epidemic.pojo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DataInfoHandler {

    @Value("${epidemic.url}")
    private String url;
    @Value("${epidemic.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    //同步数据到Redis中
    public void syncEpidemicData(){
        //1.处理请求路径(接口请求路径必须为"url?/key=key")
        String epidemicUrl = url + "?key=" + key;
        try{
            //2.从数据接口中获取数据
            DataInfo dataInfo = restTemplate.getForObject(epidemicUrl, DataInfo.class);
            //3.判断,如果没有获取到数据,则直接结束
            if (dataInfo == null) {
                return;
            }

            //4.如果有数据,则解析数据,并把疫情数据存储在redis,存储时间90分钟
            //4.1 把疫情"整体信息"转换为json
            String json = mapper.writeValueAsString(dataInfo);
            //4.2 把疫情"整体信息"存储在redis中:(key:"dataInfo_json",value:"整体信息")
            redisTemplate.opsForValue().set(Constant.REDIS_KEY_EPIDEMIC_JSON, json, 900, TimeUnit.MINUTES);
            //4.3 获取各省的疫情统计信息
            List<Province> pros = dataInfo.getNewslist();
            Map<String,String> mapProvince = new HashMap<>();
            //4.4 遍历所有的省份信息
            if(pros != null){
                for (Province pro : pros) {
                    //4.5 把每个省份的信息转换为json
                    String proJson = mapper.writeValueAsString(pro);
                    //4.6 把每个省份的信息单独存储一份(key:"省份名称(简写)",value:"省份信息")
                    mapProvince.put(pro.getProvinceName().substring(0,2),proJson);
                }
                redisTemplate.opsForHash().putAll(Constant.REDIS_KEY_EPIDEMIC_PROVINCE,mapProvince);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
