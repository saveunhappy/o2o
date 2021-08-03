package com.imooc.o2o.enums;

import lombok.Getter;

@Getter
public enum HeadLineStateEnum {
	SUCCESS(0, "创建成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "头条信息为空");

	private int state;

	private String stateInfo;

	HeadLineStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}


	public static HeadLineStateEnum stateOf(int index) {
		for (HeadLineStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
