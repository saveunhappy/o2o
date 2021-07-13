package com.imooc.o2o.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"),
    SUCCESS(1, "操作成功"), PASS(2, "认证通过"),
    INNER_ERROR(-1001, "内部系统错误"), NULL_SHOPID(-1002, "ShopId为空"),
    NULL_SHOP(-1003,"shop信息为空");
    private final int state;
    private final String stateInfo;

    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
     * 根据传入的state返回对应的enum值
     */
    public static ShopStateEnum stateOf(int state) {
        //这个values是Enumeration这个类中的，因为你创建了一个enum实际上就是继承了Enumeration
        //这个类，所以可以直接调用
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
