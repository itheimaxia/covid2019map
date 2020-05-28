package com.itheima.epidemic.pojo;

import lombok.Data;

import java.util.List;

/**
 * 疫情信息数据实体类: 用来接收新浪接口所返回回来的数据
 */
@Data
public class DataInfo {
    //响应码
    private Integer code;
    //响应描述
    private String msg;
    //各省份疫情数据
    private List<Province> newslist;
}
