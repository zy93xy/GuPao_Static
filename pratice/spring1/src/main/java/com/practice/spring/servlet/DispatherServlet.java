package com.practice.spring.servlet;



import com.practice.spring.anno.ZkHandlerMapping;
import com.practice.spring.annotation.Autowried;
import com.practice.spring.annotation.Controller;
import com.practice.spring.annotation.RequestMapping;
import com.sun.istack.internal.Nullable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.practice.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-30
 */

public class DispatherServlet extends HttpServlet {

    protected final Log logger = LogFactory.getLog("DispatherServlet");

    private Properties contextConfig = new Properties();

    private Map<String,Object> beanMap = new ConcurrentHashMap<String,Object>();

    private List<String> classNames = new ArrayList<String>();

    /** List of HandlerMappings used by this servlet */
    @Nullable
    private List<ZkHandlerMapping> handlerMappings;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("已经调用了");
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //载入
        doScanner(contextConfig.getProperty("scanPackage"));
        //注册
        doRegistry();

        //如果是SpringMVC会多设计一个HnandlerMapping

        //将@RequestMapping中配置的url和一个Method关联上
        //以便于从浏览器获得用户输入的url以后，能够找到具体执行的Method通过反射去调用
        initHandlerMapping();
    }

    /**
     * 在没有这个方法时，无法进行@RequestMapping 的访问
     */
    private void initHandlerMapping() {
        //实现
        if(classNames==null&&classNames.size()<1) return;

        for (String className: classNames
             ) {
            try {
                Class<?> clazz = Class.forName(className);
                if(!clazz.isAnnotationPresent(RequestMapping.class)) return;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private    void doLoadConfig(String location){
        //在Spring中是通过Reader去查找和定位对不对
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        logger.info("======="+is);

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
    }

    private void doScanner(String packageName) {
        logger.debug("packageName====="+packageName);
//扫描
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.","/"));
        logger.debug("url===="+url);
        File classDir = new File(url.getFile());
        logger.debug("扫描文件的路径"+classDir);
        for (File file : classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(packageName + "." +file.getName());
            }else {
                classNames.add(packageName + "." + file.getName().replace(".class",""));
            }
        }
    }
    private void doRegistry() {

        if(classNames.isEmpty()){ return;}

        try{

            for(String className : classNames){
                Class<?> clazz = Class.forName(className);


                //在Spring中用的多个子方法来处理的 ，为判断注释
                if(clazz.isAnnotationPresent(Controller.class)){

                    String beanName = lowerFirstCase(clazz.getSimpleName());

                    //在Spring中在这个阶段不是不会直接put instance，这里put的是BeanDefinition
                    beanMap.put(beanName,clazz.newInstance());

                }else if(clazz.isAnnotationPresent(Service.class)){

                    Service service = clazz.getAnnotation(Service.class);

                    //默认用类名首字母注入
                    //如果自己定义了beanName，那么优先使用自己定义的beanName
                    //如果是一个接口，使用接口的类型去自动注入

                    //在Spring中同样会分别调用不同的方法 autowriedByName autowritedByType

                    String beanName = service.value();
                    if("".equals(beanName.trim())){
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }

                    Object instance = clazz.newInstance();
                    //存入
                    beanMap.put(beanName,instance);

                    Class<?>[] interfaces = clazz.getInterfaces();

                    for (Class<?> i :interfaces){
                        beanMap.put(i.getName(),instance);
                    }

                }else{
                    continue;
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }



    //
    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;//装成大写的
        logger.debug(chars);
        return String.valueOf(chars);
    }

    private  void doAutowired(){
        if(beanMap==null&&beanMap.size()>0) return;
        for (Map.Entry<String,Object> entry: beanMap.entrySet()
             ) {

            Field [] fields  = entry.getValue().getClass().getDeclaredFields();//返回由这个注解的类的所有的属性和方法
            for (Field field: fields
                 ) {
                //判断有没有被Autowried注释的属性
                if(!field.isAnnotationPresent(Autowried.class)) continue;
                Autowried autowried =field.getAnnotation(Autowried.class);// 获取被注解成bean的组建

                String beanName = autowried.values().trim();//获取的bean的名称，由value值取得
                if("".equals(beanName)) beanName = field.getType().getName();//获取class表示申明的类型的名称

                field.setAccessible(true);//可以访问的
                try {
                    //
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
