package com.zakyoung.springboot.vue.dao;


import com.zakyoung.springboot.vue.entities.Persons;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-03-25
 */
//@Repositor
@Mapper //@Mapper将UserDao声明为一个Mapper接口
public interface PersonDao<T> {
    @Results({ //@Results是结果映射列表，@Result中property是User类的属性名，colomn是数据库表的字段名
            @Result(property = "id",column = "id"),
            @Result(property = "create_datetime",column = "create_time"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "zone",column = "zone")

    })


        //绑定
        @Select("select * from persons")//@Select, @Insert 分别代表了执行的真实SQL
        public List<Persons> getall();

        //添加
        @Insert("INSERT INTO persons(username, sex) VALUES (#{username}, #{sex})")
        public void add(Persons person);

        //删除
        @Delete("delete FROM persons where id=#{pid}")
        public void delete(@RequestParam long pid);

        //根据id获取对象
        @Select("select * from persons where id=#{pid}")
        public Persons getid(int pid);

        //修改
        @Update("update persons set phone = #{phone} where id=#{id}")
        public void update(Persons person);
}
