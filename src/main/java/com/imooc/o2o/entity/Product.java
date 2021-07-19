package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {
    //商品id
    private Long productId;
    //商品名称
    private String productName;
    //商品描述
    private String productDesc;
    //简略图
    private String imgAddr;
    //平常价格
    private String normalPrice;
    // 商品积分
    private Integer point;
    //打折后的价格
    private String promotionPrice;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
    //0.下架，1。在前端展示系统展示
    private Integer enableStatus;
    //商品的照片，是一个List,因为能有好多个照片
    private List<ProductImg> productImgList;
    //商品属于哪个类别
    private ProductCategory productCategory;
    //在哪个店铺卖
    private Shop shop;
}
