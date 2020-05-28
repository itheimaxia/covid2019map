package com.itheima.epidemic.pojo;

import lombok.Data;

import java.util.List;

/**
 * 省疫情信息
 */
@Data
public class Province {
    //省份名称
    private String provinceName;
    //简称
    private String provinceShortName;
    //确诊人数
    private String confirmedCount;
    //疑似病例
    private String suspectedCount;
    //治愈病例
    private String curedCount;
    //死亡病例
    private String deadCount;
    //其他描述
    private String comment;

    //下属地市疫情信息
    private List<City> cities;
}
