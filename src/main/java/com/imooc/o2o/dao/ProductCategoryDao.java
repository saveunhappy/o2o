package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
<<<<<<< HEAD

=======
import org.apache.ibatis.annotations.Param;
>>>>>>> origin/feature/basic
import java.util.List;

public interface ProductCategoryDao {
    /**
     *
     * @param shopId 店铺的id，看看店铺里面商品的类别，吃的，喝的，玩的
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
<<<<<<< HEAD
=======

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);

>>>>>>> origin/feature/basic
}
