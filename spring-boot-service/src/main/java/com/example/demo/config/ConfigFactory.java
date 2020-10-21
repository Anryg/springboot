package com.example.demo.config;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @DESC: 通过这种方式直接从spring容器中获取已经实例化好了的bean对象
 * @Auther: Anryg
 * @Date: 2020/9/15 17:49
 */
@Deprecated
@Component
public class ConfigFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @DESC: 通过bean的名字获取bean对象
     * */
    public static Object getBean(String beanName){
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
