package com.imooc.o2o.enums;

import lombok.Getter;

@Getter
public enum AreaStateEnum {
	OFFLINE(-1, "非法区域"), SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(
			-1002, "区域信息为空");

	private int state;

	private String stateInfo;

	AreaStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}


	public static AreaStateEnum stateOf(int index) {
		for (AreaStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
