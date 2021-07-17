package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 根据shopCondition分页返回相应店铺列表
     * @param shopCondition 条件
     * @param pageIndex 第几页
     * @param pageSize 取多少条
     * @return DTO
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

    /**
     * 通过店铺Id获取店铺信息
     * @param shopId 店铺Id
     * @return shop实例
     */
    Shop getByShopId(long shopId);
    /**
     *
     * @param shop 店铺信息
     * @param shopImgInputStream 对图片的处理
     * @param fileName 文件名称
     * @return DTO对象
     */
    //shopImgInputStream是无法获取到文件名字的，获取名字是要获取到拓展名
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;

    /*
     * 修改店铺信息
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName)throws ShopOperationException;
}
