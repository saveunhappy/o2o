package com.imooc.o2o.dao.split;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDbType() {
        //这个ThreadLocal就是一个map，你可以put和get
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     *
     * @param str 设置线程的DbType
     */
    public static void setDbType(String str){
        log.debug("所使用的数据源为:" +str);
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDbType(){
        contextHolder.remove();
    }
}
