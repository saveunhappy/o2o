package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WechatAuth {
    //微信id，就是自增的一个id
    private Long wechatAuthId;
    //openId,微信的openId
    private String openId;
    //创建时间
    private Date createTime;
    //和用户相关联
    private PersonInfo personInfo;
}
