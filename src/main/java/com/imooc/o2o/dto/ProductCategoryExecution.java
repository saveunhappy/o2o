package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import lombok.Data;

import java.util.List;
@Data
public class ProductCategoryExecution {
    //状态code
    private int state;
    //状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {

    }
    //操作失败的时候返回的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }
}
