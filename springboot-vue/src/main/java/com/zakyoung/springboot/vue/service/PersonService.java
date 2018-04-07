package com.zakyoung.springboot.vue.service;

import com.zakyoung.springboot.vue.dao.PersonDao;
import com.zakyoung.springboot.vue.entities.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-03-25
 */
@Service //声明成一个spring bean
public class PersonService {
    @Autowired
    private  PersonDao p;

    public List<Persons> getall(){

        return  p.getall();
    };

    public void delete(long pid){
        p.delete(pid);
    };

}
