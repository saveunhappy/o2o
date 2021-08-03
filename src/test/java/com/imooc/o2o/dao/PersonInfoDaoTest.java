package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoDaoTest extends BaseTest {
    @Autowired
    private PersonInfoDao personInfoDao;
    @Test
    public void testAInsertPersonInformation() throws Exception{
        //设置新增的用户信息
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("我爱你");
        personInfo.setGender("女");
        personInfo.setUserType(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        personInfo.setEnableStatus(1);
        int effectedNum = personInfoDao.insertPersonInfo(personInfo);
        assertEquals(1,effectedNum);
    }
    @Test
    public void testBQueryPersonInfoById() throws Exception{
        long userId = 8;
        PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
        System.out.println(personInfo.getName());
    }
}