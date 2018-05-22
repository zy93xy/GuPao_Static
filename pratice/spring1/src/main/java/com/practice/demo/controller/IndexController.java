package com.practice.demo.controller;


import com.practice.demo.service.IndexService;
import com.practice.spring.annotation.Autowried;
import com.practice.spring.annotation.Controller;
import com.practice.spring.annotation.RequestMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
@Controller
 public  class IndexController {
    Log logger = LogFactory.getLog(IndexController.class);

    @Autowried
    private IndexService indexService;
    @RequestMapping("/index")
    public void first(){
        logger.debug("已经进入controller");
        indexService.springOne();
    }
}
