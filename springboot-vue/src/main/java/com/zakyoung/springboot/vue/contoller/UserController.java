package com.zakyoung.springboot.vue.contoller;

import com.zakyoung.springboot.vue.entities.Persons;
import com.zakyoung.springboot.vue.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-03-25
 */

@RestController
//@EnableAutoConfiguration
@RequestMapping("/testboot")
public class UserController {
    @Autowired
    private PersonService ps;
    @RequestMapping("/show")
    public List<Persons> findAll(){
        List<Persons> lp = ps.getall();
        System.out.print(lp.get(0));

        return lp;
    }

    @RequestMapping("getuser")
    public Persons getUser() {
        Persons user = new Persons();
        user.setUsername("zhangyang");
        return user;
    }

}
