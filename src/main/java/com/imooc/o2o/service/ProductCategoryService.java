package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     *  查询指定某个店铺下的所有商品类别信息
     * @param shopId 店铺id
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量添加商品类别
     * @param productCategoryList 商品列表
     * @return
     * @throws ProductOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductOperationException;

    /**
     * 将此类别下面的商品里的类别id置为空，再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
        throws ProductOperationException;
}
