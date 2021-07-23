package com.imooc.o2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	/**
	 * 首页路由
	 * 
	 * @return
	 */
	@GetMapping("/index")
	private String index() {
		return "frontend/index";
	}

	/**
	 * 商品列表页路由
	 * 
	 * @return
	 */
	@GetMapping("/shoplist")
	private String showShopList() {
		return "frontend/shoplist";
	}

	/**
	 * 店铺详情页路由
	 * 
	 * @return
	 */
	@GetMapping("/shopdetail")
	private String showShopDetail() {
		return "frontend/shopdetail";
	}

	/**
	 * 商品详情页路由
	 * 
	 * @return
	 */
	@GetMapping("/productdetail")
	private String showProductDetail() {
		return "frontend/productdetail";
	}
}
