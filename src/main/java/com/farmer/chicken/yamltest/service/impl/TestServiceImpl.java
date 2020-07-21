package com.farmer.chicken.yamltest.service.impl;

import com.farmer.chicken.yamltest.bean.ParamBean;
import com.farmer.chicken.yamltest.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: sulei
 * @Date: 2020-05-16 19:39
 * @Discription:
 */
@Service
public class TestServiceImpl implements TestService {

    static {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader + "当前上下文的classLoader");
        System.out.println(Thread.currentThread().getName() + "当前线程名称");
    }

    @Override
    public ParamBean get() {
        ParamBean p = new ParamBean();
        p.setMethodName("正常方法");
        return p;
    }
}