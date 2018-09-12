package com.reactionsSupport.beam.start;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

public class MyBeanPostProcessor implements BeanPostProcessor {

    private Logger log = Logger.getLogger("MyBeanPostProcessor");
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("* CREATE BEAN " + bean.getClass().getSimpleName());
        return bean;
    }
}
