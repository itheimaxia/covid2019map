package com.itheima.epidemic.service;

public interface EpidemicService {

    /**
     * 获取所有疫情数据
     * @return 所有疫情数据
     */
    public String findAll();

    /**
     * 根据省份名字,获取对应省份的疫情信息
     * @param provinceShortName 省份名称(简写)
     * @return 该省份疫情数据
     */
    public String findProvinceByShortName(String provinceShortName);
}