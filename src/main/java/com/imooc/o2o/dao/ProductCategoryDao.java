package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    /**
     *
     * @param shopId 店铺的id，看看店铺里面商品的类别，吃的，喝的，玩的
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}
