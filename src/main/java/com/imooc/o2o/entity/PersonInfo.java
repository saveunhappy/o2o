package com.imooc.o2o.entity;

import lombok.Data;

import java.util.Date;
//这个其实也是微信实体类和自定义结合的，比如这里的name就是微信里面的nickname
@Data
public class PersonInfo {
    //用户id
    private Long userId;
    //用户名称
    private String name;
    //用户头像，是一个url
    private String profileImg;
    //用户邮箱
    private String email;
    //用户性别
    private String gender;
    //是否被禁用，0是被禁用，1是可使用，默认是0
    private Integer enableStatus;
    //1.顾客 2.店家 3.超级管理员
    private Integer userType;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;
}
