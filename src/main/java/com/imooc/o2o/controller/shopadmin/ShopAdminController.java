package com.imooc.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    //店铺操作添加修改
    @GetMapping("/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }
    //店铺列表
    @GetMapping("/shoplist")
    public String shoplist() {
        return "shop/shoplist";
    }
    //商品管理页，就是商铺信息，商品管理，类别管理之类的，有几个按钮
    @GetMapping("/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }
    //商品分类，有优先级的那个
    @GetMapping("/productcategorymanagement")
    public String shopProductCategoryManagement() {
        return "shop/productcategorymanagement";
    }
    //商品编辑，有缩略图的那个，优先级，积分，原价，现价，详情图片。
    @GetMapping("/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }
    //商品管理
    @GetMapping("/productmanagement")
    public String productManagement(){
        return "shop/productmanagement";
    }
}
