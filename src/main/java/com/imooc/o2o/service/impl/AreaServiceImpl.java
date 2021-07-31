package com.imooc.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.exceptions.AreaOperationException;
import com.imooc.o2o.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public List<Area> getAreaList() {
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        //先看看这个key是否在redis中
        if(!jedisKeys.exists(AREALISTKEY)){
            //没有的话，就去数据库中查询
            areaList = areaDao.queryArea();
            try {
                //把这个List对象转换成String对象
                String jsonString = mapper.writeValueAsString(areaList);
                jedisStrings.set(AREALISTKEY,jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }else {
            //这个就是redis中有对应的key，需要转换成java对象
            String jsonString = jedisStrings.get(AREALISTKEY);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                //再把String对象转换为List<Area>对象
                areaList = mapper.readValue(jsonString,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
