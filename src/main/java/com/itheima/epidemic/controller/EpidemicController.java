package com.itheima.epidemic.controller;

import com.itheima.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")   //允许被跨域访问
public class EpidemicController {

    @Autowired
    private EpidemicService epidemicService;

    /**
     * 获取所有疫情数据
     *
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll() {
        return epidemicService.findAll();
    }

    /**
     * 根据省份名称,获取指定省份疫情数据
     *
     * @param provinceShortName 省份名称(简写)
     * @return 该省份疫情数据
     */
    @RequestMapping("/findProvinceByShortName")
    public String findProvinceByShortName(String provinceShortName) {
        //调用service,获取数据
        return epidemicService.findProvinceByShortName(provinceShortName);
    }
}