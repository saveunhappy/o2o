package com.imooc.o2o.enums;

import lombok.Getter;

/**
 * 使用枚举表述常量数据字典
 */
@Getter
public enum PersonInfoStateEnum {

	SUCCESS(0, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "用户信息为空");

	private final int state;

	private final String stateInfo;

	PersonInfoStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}


	public static PersonInfoStateEnum stateOf(int index) {
		for (PersonInfoStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}