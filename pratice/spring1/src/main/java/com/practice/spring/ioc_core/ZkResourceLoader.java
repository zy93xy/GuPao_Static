package com.practice.spring.ioc_core;


import java.util.Properties;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */
public  class ZkResourceLoader {

    private  Properties contextConfig = new Properties();

    /*public   void doLoadConfig(String location){
        //在Spring中是通过Reader去查找和定位对不对
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));

        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != is){is.close();}
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }*/
}
