package com.zakyoung.springboot.vue.contoller;

import com.zakyoung.springboot.vue.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-03-25
 */
@Controller
public class IndexController {
    @Autowired
    private PersonService ps;
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/deleteone")
    public String deleteById(@RequestParam(name = "id") long pid ){
        ps.delete(pid);
        return "index";
    }
}
