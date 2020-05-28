package com.itheima.epidemic.pojo;

import lombok.Data;

/**
 * 各地市疫情信息
 */
@Data
public class City {
    //省份名称
    private String cityName;
    //确诊人数
    private String confirmedCount;
    //疑似病例
    private String suspectedCount;
    //治愈病例
    private String curedCount;
    //死亡病例
    private String deadCount;
}
