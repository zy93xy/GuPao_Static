package com.practice.spring.ioc_core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
public class ZkSimpleBeanDefinitionRegistry {
    private Map<String,Object> beanMap = new ConcurrentHashMap<String,Object>();

    private List<String> classNames = new ArrayList<String>();


}
