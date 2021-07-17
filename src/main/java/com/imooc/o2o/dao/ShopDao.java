package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     *
     * @param shopCondition 条件
     * @param rowIndex 从第几页开始
     * @param pageSize 每页多少条数据源
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,
    @Param("pageSize") int pageSize);

    /**
     *
     * @param shopCondition 查询店铺数量
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
    /**
     *
     * @param shopId 通过shop id 查询店铺
     * @return
     */
    Shop queryByShopId(long shopId);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
