package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HeadLine {
    //头条id
    private Long lineId;
    //头条名称
    private String lineName;
    //头条链接，就是url
    private String lineLink;
    //头条图片
    private String lineImg;
    //头条的权重
    private Integer priority;
    //0.不可用，1.可用
    private Integer enableStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
}
