package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductImg {
    //图片id
    private Long productImgId;
    //图片地址
    private String imgAddr;
    //图片描述
    private String imgDesc;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //和哪个商品的id相关联
    private Long productId;
}
