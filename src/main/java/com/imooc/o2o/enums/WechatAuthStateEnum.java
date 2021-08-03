package com.imooc.o2o.enums;

import lombok.Getter;

@Getter
public enum WechatAuthStateEnum {
	LOGINFAIL(-1, "openId输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006, "注册信息为空");

	private final int state;

	private final String stateInfo;

	WechatAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static WechatAuthStateEnum stateOf(int index) {
		for (WechatAuthStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
