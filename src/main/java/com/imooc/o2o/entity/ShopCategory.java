package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCategory {
    //店铺类别id
    private Long shopCategoryId;
    //店铺类别名称
    private String shopCategoryName;
    //店铺类别描述
    private String ShopCategoryDesc;
    //店铺类别照片
    private String shopCategoryImg;
    //店铺权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
    //其实就是要一个上级id，可以一直往上找。
    private ShopCategory parent;
}
