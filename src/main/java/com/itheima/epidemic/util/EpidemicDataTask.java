package com.itheima.epidemic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@EnableScheduling
@Component
public class EpidemicDataTask {

    @Autowired
    private DataInfoHandler dataInfoHandler;

    @Scheduled(fixedDelay = 1000 * 60 * 60)  //间隔60分钟
    public void initData(){
        System.out.println("获取疫情数据:"+new Date().toLocaleString());
        //每隔60分钟, 获取一次疫情数据
        dataInfoHandler.syncEpidemicData();
    }

}
