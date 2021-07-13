package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategory {
    //商品类别id
    private Long productCategoryId;
    //店铺id，就是哪个店铺卖的
    private Long shopId;
    //商品类别名称
    private String productCategoryName;
    //商品类别描述
    private String productCategoryDesc;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
}
