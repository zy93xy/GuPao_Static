package com.zy.mybatis.jdbc;

import java.lang.reflect.Proxy;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-01
 */
public class SqlSession {


    public <T> T getMapper(Class<T> clazz){
            return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz} ,new MapperProxy(this,clazz));
    };
}
