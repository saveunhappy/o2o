package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
<<<<<<< HEAD
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void testQueryProductCategoryByShopId() throws Exception{
        long shopId = 1;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为："+productCategories.size());
    }

=======
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
//按照名字的顺序执行，都是test开始的，那么就ABC就行了
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    //@Ignore
    public void testBQueryProductCategoryByShopId() throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为：" + productCategories.size());
    }

    @Test
    //@Ignore
    public void testABatchInsertProductCategory() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别1");
        productCategory.setCreateTime(new Date());
        productCategory.setPriority(1);
        productCategory.setShopId(1L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别2");
        productCategory2.setCreateTime(new Date());
        productCategory2.setPriority(2);
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectCount = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2, effectCount);
    }

    @Test
    public void testCDeleteProductCategory() throws Exception {
        long shopId = 1;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory pc : productCategoryList) {
            if ("商品类别1".equals(pc.getProductCategoryName()) || "商品类别2".equals(pc.getProductCategoryName())){
                int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
                assertEquals(1,effectedNum);
            }
        }
    }
>>>>>>> origin/feature/basic
}