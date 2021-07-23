package com.imooc.o2o.util;

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        //这里就是一共返回多少条数据了，就是，比如传入1,5，结果就5条数据。
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
