package com.practice.demo.service.impl;

import com.practice.demo.service.IndexService;
import com.practice.spring.annotation.Service;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public void springOne() {
        System.out.print("这里是调用service的实现");
    }
}
