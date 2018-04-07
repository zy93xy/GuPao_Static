package com.zy.mybatis.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-01
 */
public class MapperProxy<T> implements InvocationHandler{
    public final SqlSession mySqlSession;

    public final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
        this.mySqlSession = sqlSession;
        this.mapperInterface = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
