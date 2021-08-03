package com.imooc.o2o.controller.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopService shopService;

	/**
	 * 返回商品列表页里的ShopCategory列表(二级或者一级)，以及区域信息列表
	 * 就是首页的全部商店，你第一次访问，肯定是没有指定parentId的，所以首页就是parentId为null的ShopCategory
	 * 顺带把区域列表也给一同返回回去了。http://localhost:8080/o2o/frontend/listshopspageinfo?parentId=
	 * 当你要进入一个商品分类的时候，就有parentId了，你刚开始在index下面，比如你要进入二手市场，这个时候二手市场就作为一个parent了
	 *  这个接口主要还是查询二级店铺，现在去看listshop                                                                                                                                parent_id
	 * 10	二手市场	二手商品交易	/upload/images/item/shopcategory/2017061223272255687.png	100	2017-06-04 20:10:58	2017-06-12 23:27:22
	 * 14	旧车	旧车	/upload/images/item/shopcategory/2017060420315183203.png	    80	2017-06-04 20:31:51	2017-06-04 20:31:51	10
	 * 15	二手书籍	二手书籍	/upload/images/item/shopcategory/2017060420322333745.png	    79	2017-06-04 20:32:23	2017-06-04 20:32:23	10
	 * @param request
	 * @return
	 */
	@GetMapping("/listshopspageinfo")
	@ResponseBody
	private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 试着从前端请求中获取parentId
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			// 如果parentId存在，则取出该一级ShopCategory下的二级ShopCategory列表
			try {
				ShopCategory shopCategoryCondition = new ShopCategory();
				ShopCategory parent = new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else {
			try {
				// 如果parentId不存在，则取出所有一级ShopCategory(用户在首页选择的是全部商店列表)
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			// 获取区域列表信息
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 获取指定查询条件下的店铺列表
	 * 上面那个就是在查询二级列表，这个就是详细的信息了，因为上面还是有button让你来选择的。
	 * 分页信息啊，类别啊。街道啊，这个就是进一步区分
	 * @param request
	 * @return
	 */
	@GetMapping("/listshops")
	@ResponseBody
	private Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		// 获取一页需要显示的数据条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 非空判断
		if ((pageIndex > -1) && (pageSize > -1)) {
			// 试着获取一级类别Id
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");
			// 试着获取特定二级类别Id
			long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
			// 试着获取区域Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			// 试着获取模糊查询的名字
			String shopName = HttpServletRequestUtil.getString(request, "shopName");
			// 获取组合之后的查询条件
			Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
			// 根据查询条件和分页信息获取店铺列表，并返回总数
			ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}

		return modelMap;
	}

	/**
	 * 组合查询条件，并将条件封装到ShopCondition对象里返回
	 * 
	 * @param parentId
	 * @param shopCategoryId
	 * @param areaId
	 * @param shopName
	 * @return
	 */
	private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		if (parentId != -1L) {
			// 查询某个一级ShopCategory下面的所有二级ShopCategory里面的店铺列表
			ShopCategory childCategory = new ShopCategory();
			ShopCategory parentCategory = new ShopCategory();
			parentCategory.setShopCategoryId(parentId);
			childCategory.setParent(parentCategory);
			shopCondition.setShopCategory(childCategory);
		}
		if (shopCategoryId != -1L) {
			// 查询某个二级ShopCategory下面的店铺列表
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if (areaId != -1L) {
			// 查询位于某个区域Id下的店铺列表
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}

		if (shopName != null) {
			// 查询名字里包含shopName的店铺列表
			shopCondition.setShopName(shopName);
		}
		// 前端展示的店铺都是审核成功的店铺
		shopCondition.setEnableStatus(1);
		return shopCondition;
	}
}
