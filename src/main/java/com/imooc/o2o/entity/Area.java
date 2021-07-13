package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Area {
    //ID
    private Integer areaId;
    //名称
    private String areaName;
    //权重，谁的权重高，谁就能显示靠前
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
}
