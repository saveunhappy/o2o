package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LocalAuth {
    //本地账号的id，自增的
    private Long localAuthId;
    //用户名
    private String username;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //最后修改时间
    private String lastEditTime;
    //和用户信息相关联
    private PersonInfo personInfo;
}
