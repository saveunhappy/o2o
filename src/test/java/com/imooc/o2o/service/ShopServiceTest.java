package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void testGetShopList() throws Exception{
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
        System.out.println("店铺列表数为："+se.getShopList().size());
        System.out.println("店铺总数为："+se.getCount());

    }

    @Test
    @Ignore
    public void testModifyShop() throws Exception {
        Shop shop = new Shop();
        shop.setShopId(1L);
        File shopImg = new File("D:\\360downloads\\2.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
<<<<<<< HEAD
        ShopExecution shopExecution = shopService.modifyShop(shop, inputStream, "2.jpg");
=======
        ImageHolder imageHolder = new ImageHolder("2.jpg",inputStream);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
>>>>>>> origin/feature/basic
        System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());

    }


    @Test
    @Ignore
    public void testAddShop() throws Exception {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\dev\\xiaohuangren.jpg");
        InputStream is = new FileInputStream(shopImg);
<<<<<<< HEAD
        ShopExecution shopExecution = shopService.addShop(shop, is, shopImg.getName());
=======
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
>>>>>>> origin/feature/basic
        //因为成功了，才是CHECK的状态，不是的话，就是ShopOperationException抛出来了
        //这个返回的对象就是成功的时候才会返回，而且把值给设置进去了
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}