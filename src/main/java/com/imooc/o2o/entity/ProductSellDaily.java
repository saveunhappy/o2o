package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

//顾客消费的商品映射
@Data
public class ProductSellDaily {
	// 哪天的销量，精确到天
	private Date createTime;
	// 销量
	private Integer total;
	// 商品信息实体类
	private Product product;
	// 店铺信息实体类
	private Shop shop;


}
