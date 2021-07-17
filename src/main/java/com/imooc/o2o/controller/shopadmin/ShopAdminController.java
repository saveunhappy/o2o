package com.imooc.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    @GetMapping("/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }

    @GetMapping("/shoplist")
    public String shoplist() {
        return "shop/shoplist";
    }

    @GetMapping("/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }
    @GetMapping("/productcategorymanagement")
    public String shopProductCategoryManagement() {
        return "shop/productcategorymanagement";
    }

}
