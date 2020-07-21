package com.farmer.chicken.yamltest.service;

import com.farmer.chicken.yamltest.bean.ParamBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzr@missfresh.cn
 * @date 2019/12/20 17:25
 */

@Component
public class YmlHandler{

    @Autowired
    private TestService testService;

    public ParamBean execute(String orderId, int id){
        ParamBean paramBean1 = testService.get();
        return paramBean1;
    }


    public ParamBean get(){
        System.out.println("进入本地get方法的第一行");
        ParamBean p = new ParamBean();
        return p;
    }



}
