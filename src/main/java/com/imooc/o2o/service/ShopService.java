package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    //shopImgInputStream是无法获取到文件名字的，获取名字是要获取到拓展名
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
