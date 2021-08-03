package com.imooc.o2o;
/*
配置Spring和Junit整合，Junit启动时加载springIOC容器
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-web.xml","classpath:spring/spring-redis.xml"})
@WebAppConfiguration("src/main/resources")
public class BaseTest {
    //TODO  @WebAppConfiguration("src/main/resources")如果不加这个注解，默认是从webapp目录下找
    //resources文件夹，所以找不到spring-dao.xml，pring/spring-web.xml文件
    //然后因为没有找到commons-fileupload的jar包，所以报错了
}
