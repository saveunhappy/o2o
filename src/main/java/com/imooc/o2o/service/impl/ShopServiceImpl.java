package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
<<<<<<< HEAD
import com.imooc.o2o.util.ImageUtils;
=======
import com.imooc.o2o.util.ImageUtil;
>>>>>>> origin/feature/basic
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
<<<<<<< HEAD
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, pageIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if(shopList != null){
            se.setShopList(shopList);
            se.setCount(count);
        }else{
=======
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        //TODO 这里改了。
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
>>>>>>> origin/feature/basic
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
=======
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
>>>>>>> origin/feature/basic
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effecedNum = shopDao.insertShop(shop);
            if (effecedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail.getImage() != null) {
                    //存储图片
                    try {
<<<<<<< HEAD
                        addShopImg(shop, shopImgInputStream, fileName);
=======
                        addShopImg(shop, thumbnail);
>>>>>>> origin/feature/basic
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effecedNum = shopDao.updateShop(shop);
                    if (effecedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ShopOperationException("add Shop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
<<<<<<< HEAD
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
=======
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
>>>>>>> origin/feature/basic
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            //1.判断是否需要处理图片
<<<<<<< HEAD
            try{

            if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                //这里应该叫shopImgPath比较好，毕竟只是一个路径，我还以为图片呢
                if (tempShop.getShopImg() != null) {
                    ImageUtils.deleteFileOrPath(tempShop.getShopImg());
                }
                //为什么不用tempShop而是用shop。因为addShopImg会对shop进行操作，会传入一个新的图片地址
                //而且传进来的shop可能已经带着一些修改信息了，但是你查出来的还是数据库里面查出来的那个原始的
                //所以要传shop
                addShopImg(shop, shopImgInputStream, fileName);
            }
            //2.更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else {
                Shop queryByShopId = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,queryByShopId);
            }}catch (Exception e){
=======
            try {

                if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    //这里应该叫shopImgPath比较好，毕竟只是一个路径，我还以为图片呢
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    //为什么不用tempShop而是用shop。因为addShopImg会对shop进行操作，会传入一个新的图片地址
                    //而且传进来的shop可能已经带着一些修改信息了，但是你查出来的还是数据库里面查出来的那个原始的
                    //所以要传shop
                    addShopImg(shop, thumbnail);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    Shop queryByShopId = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, queryByShopId);
                }
            } catch (Exception e) {
>>>>>>> origin/feature/basic
                throw new ShopOperationException("modifyShop error" + e.getMessage());
            }
        }
    }

<<<<<<< HEAD
    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtils.generateThumbnail(shopImgInputStream, fileName, dest);
=======
    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
>>>>>>> origin/feature/basic
        shop.setShopImg(shopImgAddr);
    }
}
