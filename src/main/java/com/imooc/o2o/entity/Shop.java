package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {
    //店铺id
    private Long shopId;
    //店铺名称
    private String shopName;
    //店铺描述
    private String shopDesc;
    //店铺地址
    private String shopAddr;
    //店铺电话
    private String phone;
    //店铺图片，也是一个url
    private String shopImg;
    //店铺权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
    //-1不可用，0.审核中，1，可用
    private Integer enableStatus;
    //超级管理员给店家的提醒
    private String advice;
    //这个店铺属于哪个区域
    private Area area;
    //谁创建的
    private PersonInfo owner;
    //表示店铺属于哪个类别的，奶茶？还是大盘鸡？还是烧烤
    private ShopCategory shopCategory;


}
